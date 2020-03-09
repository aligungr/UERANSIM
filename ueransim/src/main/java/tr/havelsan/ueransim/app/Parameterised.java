package tr.havelsan.ueransim.app;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import sun.misc.Signal;
import sun.misc.SignalHandler;
import tr.havelsan.ueransim.BaseFlow;
import tr.havelsan.ueransim.app.sim.MtsEapAkaAttributes;
import tr.havelsan.ueransim.app.sim.MtsIEEapMessage;
import tr.havelsan.ueransim.app.sim.MtsProtocolEnumRegistry;
import tr.havelsan.ueransim.exceptions.MtsException;
import tr.havelsan.ueransim.inputs.RegistrationInput;
import tr.havelsan.ueransim.mts.ImplicitTypedObject;
import tr.havelsan.ueransim.mts.MtsConstruct;
import tr.havelsan.ueransim.mts.MtsDecoder;
import tr.havelsan.ueransim.mts.TypeRegistry;
import tr.havelsan.ueransim.nas.eap.Eap;
import tr.havelsan.ueransim.nas.eap.EapAkaPrime;
import tr.havelsan.ueransim.nas.eap.EapIdentity;
import tr.havelsan.ueransim.nas.eap.EapNotification;
import tr.havelsan.ueransim.sctp.SCTPClient;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;
import tr.havelsan.ueransim.utils.Utils;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Parameterised {

    public static void main(String[] args) throws Exception {
        initMts();

        var config = new LinkedHashMap<String, String>();
        var configYaml = (ImplicitTypedObject) MtsDecoder.decode("yaml/config.yaml");
        for (var e : configYaml.getParameters().entrySet()) {
            config.put(e.getKey(), String.valueOf(e.getValue()));
        }

        Environment.AMF_HOST = config.get("amf.host");
        Environment.AMF_PORT = Integer.parseInt(config.get("amf.port"));

        var types = new LinkedHashMap<String, Class<? extends BaseFlow>>();
        var typeNames = new ArrayList<String>();
        for (String fn : Backend.getFlowNames()) {
            var type = Backend.getFlowType(fn);
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

        Console.println(Color.BLUE, "Trying to establish SCTP connection... (%s:%s)", Environment.AMF_HOST, Environment.AMF_PORT);
        var sctpClient = new SCTPClient(Environment.AMF_HOST, Environment.AMF_PORT, Constants.NGAP_PROTOCOL_ID);
        sctpClient.start();

        catchINTSignal(sctpClient);

        Console.println(Color.BLUE, "SCTP connection established.");
        Console.printDiv();

        var scanner = new Scanner(System.in);
        while (true) {
            Console.printDiv();

            if (!sctpClient.isOpen())
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
                sctpClient.close();
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
                ctor.newInstance(sctpClient, readInputFile(key, "yaml/" + config.get(key), inputType))
                        .start();
            } else {
                ctor.newInstance(sctpClient)
                        .start();
            }
        }
    }

    private static void catchINTSignal(SCTPClient sctpClient) {
        Signal.handle(new Signal("INT"), new SignalHandler() {
            private final AtomicBoolean inShutdown = new AtomicBoolean();
            public void handle(Signal sig) {
                if (inShutdown.compareAndSet(false, true)) {
                    Console.println(Color.BLUE, "Simulator is shutdown gracefully");
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
        return MtsConstruct.construct(type, ((ImplicitTypedObject) inp).getParameters(), true);
    }

    private static void initMts() {
        try (ScanResult scanResult = new ClassGraph().enableClassInfo().ignoreClassVisibility().whitelistPackages(Constants.NAS_IMPL_PREFIX).scan()) {
            var classInfoList = scanResult.getAllClasses();
            for (var classInfo : classInfoList) {
                Class<?> clazz;
                try {
                    clazz = Class.forName(classInfo.getName());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                String typeName = Utils.getTypeName(clazz);
                TypeRegistry.registerTypeName(typeName, clazz);
            }
        }

        final Class<?>[] otherTypes = new Class[]{
                Eap.class,
                EapAkaPrime.class,
                EapIdentity.class,
                EapNotification.class,
                EapAkaPrime.EAttributeType.class,
                EapAkaPrime.ESubType.class,
                Eap.ECode.class,
                Eap.EEapType.class,
                RegistrationInput.class
        };

        for (var type : otherTypes) {
            TypeRegistry.registerTypeName(type.getSimpleName(), type);
        }

        TypeRegistry.registerCustomType(new MtsProtocolEnumRegistry());
        TypeRegistry.registerCustomType(new MtsIEEapMessage());
        TypeRegistry.registerCustomType(new MtsEapAkaAttributes());

        MtsDecoder.setFileProvider((searchDir, path) -> {
            try {
                String content = Files.readString(Paths.get(searchDir, path));
                if (path.endsWith(".yaml"))
                    content = Utils.convertYamlToJson(content);
                return content;
            } catch (NoSuchFileException e) {
                throw new MtsException("file not found: %s", e.getFile());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
