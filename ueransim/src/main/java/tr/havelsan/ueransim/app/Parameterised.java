package tr.havelsan.ueransim.app;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import tr.havelsan.ueransim.BaseFlow;
import tr.havelsan.ueransim.app.sim.MtsEapAkaAttributes;
import tr.havelsan.ueransim.app.sim.MtsIEEapMessage;
import tr.havelsan.ueransim.app.sim.MtsProtocolEnumRegistry;
import tr.havelsan.ueransim.exceptions.MtsException;
import tr.havelsan.ueransim.flows.ConnectionTry;
import tr.havelsan.ueransim.mts.MtsDecoder;
import tr.havelsan.ueransim.mts.TypeRegistry;
import tr.havelsan.ueransim.nas.eap.Eap;
import tr.havelsan.ueransim.nas.eap.EapAkaPrime;
import tr.havelsan.ueransim.nas.eap.EapIdentity;
import tr.havelsan.ueransim.nas.eap.EapNotification;
import tr.havelsan.ueransim.parameterised.RegistrationInput;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;
import tr.havelsan.ueransim.utils.Utils;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Parameterised {

    public static void main(String[] args) throws Exception {
        initMts();

        var scanner = new Scanner(System.in);
        var flowName = Utils.getCommandLineOption(args, "-f");
        var fileInput = Utils.getCommandLineOption(args, "-p");

        if (flowName == null) {
            Console.println(Color.BLUE, "Select a flow:");

            var paramFlows = new ArrayList<>();

            for (String s : Backend.getFlowNames()) {
                var flowType = Backend.getFlowType(s);
                if (flowType.equals(ConnectionTry.class) || Arrays.stream(flowType.getDeclaredConstructors())
                        .anyMatch(constructor -> {
                            if (constructor.getParameters().length != 1)
                                return false;
                            return Modifier.isPublic(constructor.getModifiers());
                        })) {
                    paramFlows.add(s);
                }
            }

            var flowNames = paramFlows.toArray(new String[0]);
            Arrays.sort(flowNames);

            for (int i = 0; i < flowNames.length; i++) {
                Console.print(Color.BLUE, i + 1 + ") ");
                Console.println(flowNames[i]);
            }

            Console.println(Color.BLUE, "Selection: ");

            int selection = scanner.nextInt();
            scanner.nextLine();

            flowName = flowNames[selection - 1];
        }

        var flow = Backend.getFlowType(flowName);
        if (flow == null) {
            Console.println(Color.RED, "Error: flow not found: " + flowName);
            return;
        }

        if (flow.equals(ConnectionTry.class)) {
            flow.getConstructor().newInstance().start();
            return;
        }

        if (fileInput == null) {
            Console.println(Color.BLUE, "Input file: ");
            fileInput = scanner.nextLine();
        }

        var registrationInput = (RegistrationInput) MtsDecoder.decode(fileInput);
        Console.println(Json.toJson(registrationInput));
        Console.printDiv();

        var ctors = Utils.streamToList(Arrays.stream(flow.getDeclaredConstructors())
                .filter(constructor -> constructor.getParameters().length == 1));
        if (ctors.size() != 1) {
            Console.println(Color.RED, "Error: multiple or no constructors found for selected flow");
            return;
        }

        ((BaseFlow) (ctors.get(0).newInstance(registrationInput))).start();
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
