package tr.havelsan.ueransim;

import sun.misc.Signal;
import sun.misc.SignalHandler;
import tr.havelsan.ueransim.core.Constants;
import tr.havelsan.ueransim.core.NasSecurityContext;
import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.core.UeData;
import tr.havelsan.ueransim.mts.ImplicitTypedObject;
import tr.havelsan.ueransim.mts.MtsConstruct;
import tr.havelsan.ueransim.mts.MtsDecoder;
import tr.havelsan.ueransim.mts.MtsInitializer;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.ngap2.NgapInternal;
import tr.havelsan.ueransim.ngap2.UserLocationInformationNr;
import tr.havelsan.ueransim.sctp.ISCTPClient;
import tr.havelsan.ueransim.sctp.MockedSCTPClient;
import tr.havelsan.ueransim.sctp.SCTPClient;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class FlowTesting {

    public static void main(String[] args) throws Exception {
        MtsInitializer.initMts();

        var scanner = new Scanner(System.in);

        var config = new LinkedHashMap<String, String>();
        var configYaml = (ImplicitTypedObject) MtsDecoder.decode("config.yaml");
        for (var e : configYaml.getParameters().entrySet()) {
            config.put(e.getKey(), String.valueOf(e.getValue()));
        }

        var types = new LinkedHashMap<String, Class<? extends BaseFlow>>();
        var typeNames = new ArrayList<String>();
        for (String fn : FlowScanner.getFlowNames()) {
            var type = FlowScanner.getFlowType(fn);
            types.put(fn, type);
            typeNames.add(fn);
        }

        var configOrder = new HashMap<String, Integer>();
        for (var entry : config.entrySet()) {
            String key = entry.getKey();
            if (key.matches("^input\\.[a-zA-Z]+$")) {
                configOrder.put(key.substring("input.".length()), configOrder.size());
            }
        }

        typeNames.sort((string1, string2) -> {
            Integer i1 = configOrder.get(string1);
            Integer i2 = configOrder.get(string2);
            if (i1 == null && i2 == null) return 0;
            if (i1 == null) return 1;
            if (i2 == null) return -1;
            return i1.compareTo(i2);
        });

        var simContext = createSimContext(configYaml);

        Console.println(Color.BLUE, "Trying to establish SCTP connection... (%s:%s)", simContext.amfHost, simContext.amfPort);
        simContext.sctpClient.start();

        catchINTSignal(simContext.sctpClient);

        Console.println(Color.BLUE, "SCTP connection established.");

        String flowName = Utils.getCommandLineOption(args, "-f");
        String yamlFile = Utils.getCommandLineOption(args, "-y");

        if (flowName != null && yamlFile != null) {
            var type = FlowScanner.getFlowType(flowName);
            if (type == null) {
                throw new RuntimeException("Flow not found: " + flowName);
            }
            var ctor = findConstructor(type);
            var inputType = ctor.getParameterCount() > 1 ? ctor.getParameterTypes()[1] : null;

            if (inputType != null) {
                ctor.newInstance(simContext, readInputFile("", yamlFile, inputType))
                        .start();
            } else {
                ctor.newInstance(simContext)
                        .start();
            }
            return;
        }

        while (true) {
            Console.printDiv();

            if (!simContext.sctpClient.isOpen())
                break;

            Console.println(Color.BLUE, "Select a flow:");
            Console.print(Color.BLUE, "0) ");
            Console.println("Close connection");
            for (int i = 0; i < typeNames.size(); i++) {
                Console.print(Color.BLUE, i + 1 + ") ");
                Console.println(typeNames.get(i));
            }
            Console.print(Color.BLUE, "Selection: ");

            int selection;
            try {
                selection = scanner.nextInt();
            } catch (Exception e) {
                Console.println(Color.YELLOW, "Invalid selection");
                continue;
            }
            scanner.nextLine();
            Console.println();

            if (selection == 0) {
                simContext.sctpClient.close();
                break;
            }

            if (selection < 1 || selection - 1 >= typeNames.size()) {
                Console.println(Color.YELLOW, "Invalid selection: " + selection);
                continue;
            }

            var selectedType = types.get(typeNames.get(selection - 1));
            var ctor = findConstructor(selectedType);
            var inputType = ctor.getParameterCount() > 1 ? ctor.getParameterTypes()[1] : null;

            if (inputType != null) {
                String key = "input." + typeNames.get(selection - 1);
                ctor.newInstance(simContext, readInputFile(key, "" + config.get(key), inputType))
                        .start();
            } else {
                ctor.newInstance(simContext)
                        .start();
            }
        }
    }

    private static SimulationContext createSimContext(ImplicitTypedObject config) {
        var params = config.getParameters();

        var simContext = new SimulationContext();

        // Parse UE Data
        {
            Map<String, Object> ud = ((ImplicitTypedObject) params.get("ueData")).getParameters();

            var ueData = new UeData();
            ueData.snn = (String) ud.get("snn");
            ueData.key = new OctetString((String) ud.get("key"));
            ueData.op = new OctetString((String) ud.get("op"));
            ueData.sqn = new OctetString((String) ud.get("sqn"));
            ueData.amf = new OctetString((String) ud.get("amf"));
            ueData.imei = (String) ud.get("imei");
            ueData.supi = (String) ud.get("supi");
            simContext.ueData = ueData;
        }

        // Parse User Location Information
        {
            simContext.userLocationInformationNr = MtsConstruct.construct(UserLocationInformationNr.class,
                    ((ImplicitTypedObject) params.get("context.userLocationInformationNr")), true);
        }

        // Parse RAN-UE-NGAP-ID
        {
            simContext.ranUeNgapId = ((Number) params.get("context.ranUeNgapId")).longValue();
        }

        // Create SCTP Client
        {
            String amfHost = params.get("amf.host").toString();
            int amfPort = (int) params.get("amf.port");
            boolean amfMocked = (boolean) params.get("amf.mocked");

            simContext.amfHost = amfHost;
            simContext.amfPort = amfPort;

            ISCTPClient sctpClient = new SCTPClient(amfHost, amfPort, Constants.NGAP_PROTOCOL_ID);

            if (amfMocked) {
                Console.println(Color.YELLOW_BOLD, "Mocked Remote is enabled.");
                sctpClient = newMockedClient((String) params.get("amf.mockedRemote"));
            }

            simContext.streamNumber = Constants.DEFAULT_STREAM_NUMBER;
            simContext.sctpClient = sctpClient;
        }

        // Create NAS Security Context
        {
            simContext.nasSecurityContext = new NasSecurityContext();
        }

        return simContext;
    }

    private static MockedSCTPClient newMockedClient(String mockedRemoteFile) {
        var mockedRemote = ((ImplicitTypedObject) MtsDecoder.decode(mockedRemoteFile)).getParameters();

        return new MockedSCTPClient(new MockedSCTPClient.IMockedRemote() {
            int messageIndex = 0;

            @Override
            public void onMessage(byte[] data, Queue<Byte[]> queue) {
                var ngapPdu = Ngap.perDecode(NGAP_PDU.class, data);
                var ngapMessage = NgapInternal.extractNgapMessage(ngapPdu);
                var nasMessage = NgapInternal.extractNasMessage(ngapPdu);
                var incomingMessage = new IncomingMessage(ngapPdu, ngapMessage, nasMessage);
                Queue<String> outs = new ArrayDeque<>();
                onMessage(incomingMessage, outs);
                while (!outs.isEmpty()) {
                    var out = outs.remove();
                    byte[] pdu = Utils.hexStringToByteArray(out);
                    Byte[] res = new Byte[pdu.length];
                    for (int i = 0; i < res.length; i++) {
                        res[i] = pdu[i];
                    }
                    queue.add(res);
                }
            }

            private void onMessage(IncomingMessage message, Queue<String> queue) {
                var mockedValues = (Object[]) mockedRemote.get("messages-in-order");
                Object mockedValue = mockedValues[messageIndex];
                if (mockedValue != null) {
                    String str = mockedValue.toString();
                    if (str.length() > 0) {
                        queue.add(str);
                    }
                }
                messageIndex++;
            }
        });
    }

    private static void catchINTSignal(ISCTPClient sctpClient) {
        Signal.handle(new Signal("INT"), new SignalHandler() {
            private final AtomicBoolean inShutdown = new AtomicBoolean();

            public void handle(Signal sig) {
                if (inShutdown.compareAndSet(false, true)) {
                    Console.println(Color.BLUE, "ueransim is shutting down gracefully");
                    sctpClient.close();
                    Console.println(Color.BLUE, "SCTP connection closed");
                    System.exit(1);
                }
            }
        });
    }

    private static Constructor<BaseFlow> findConstructor(Class<? extends BaseFlow> selectedType) {
        if (selectedType.getDeclaredConstructors().length != 1)
            throw new RuntimeException("zero or multiple constructor found for selected flow");
        return (Constructor<BaseFlow>) selectedType.getDeclaredConstructors()[0];
    }

    private static <T> T readInputFile(String key, String path, Class<T> type) {
        if (path == null || path.length() == 0)
            throw new RuntimeException("please specify flow input file (" + key + ")");
        var inp = MtsDecoder.decode(path);
        return MtsConstruct.construct(type, (ImplicitTypedObject) inp, true);
    }
}
