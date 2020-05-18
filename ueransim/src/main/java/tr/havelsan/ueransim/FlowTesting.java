package tr.havelsan.ueransim;

import sun.misc.Signal;
import sun.misc.SignalHandler;
import tr.havelsan.ueransim.contexts.SimulationContext;
import tr.havelsan.ueransim.contexts.UeData;
import tr.havelsan.ueransim.core.Constants;
import tr.havelsan.ueransim.mts.ImplicitTypedObject;
import tr.havelsan.ueransim.mts.MtsConstruct;
import tr.havelsan.ueransim.mts.MtsDecoder;
import tr.havelsan.ueransim.mts.MtsInitializer;
import tr.havelsan.ueransim.nas.impl.ies.IEImeiMobileIdentity;
import tr.havelsan.ueransim.nas.impl.ies.IEImsiMobileIdentity;
import tr.havelsan.ueransim.ngap2.UserLocationInformationNr;
import tr.havelsan.ueransim.sctp.ISCTPClient;
import tr.havelsan.ueransim.sctp.SCTPClient;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
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
            var ueData = new UeData();
            ueData.ssn = (String) params.get("ueData.ssn");
            ueData.key = new OctetString((String) params.get("ueData.key"));
            ueData.op = new OctetString((String) params.get("ueData.op"));
            ueData.sqn = new OctetString((String) params.get("ueData.sqn"));
            ueData.amf = new OctetString((String) params.get("ueData.amf"));
            ueData.imei = new IEImeiMobileIdentity((String) params.get("ueData.imei"));
            ueData.imsi = MtsConstruct.construct(IEImsiMobileIdentity.class, (ImplicitTypedObject) params.get("ueData.imsi"), true);
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

            Console.println(Color.BLUE, "Trying to establish SCTP connection... (%s:%s)", amfHost, amfPort);
            ISCTPClient sctpClient = new SCTPClient(amfHost, amfPort, Constants.NGAP_PROTOCOL_ID);

            simContext.streamNumber = Constants.DEFAULT_STREAM_NUMBER;
            simContext.sctpClient = sctpClient;
        }

        return simContext;
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
