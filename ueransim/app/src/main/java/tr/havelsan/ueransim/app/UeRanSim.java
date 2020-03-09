package tr.havelsan.ueransim.app;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import tr.havelsan.ueransim.app.sim.*;
import tr.havelsan.ueransim.utils.Json;
import tr.havelsan.ueransim.core.exceptions.DecodingException;
import tr.havelsan.ueransim.core.exceptions.EncodingException;
import tr.havelsan.ueransim.exceptions.IncorrectImplementationException;
import tr.havelsan.ueransim.mts.MtsException;
import tr.havelsan.ueransim.mts.ImplicitTypedObject;
import tr.havelsan.ueransim.mts.MtsConstruct;
import tr.havelsan.ueransim.mts.MtsDecoder;
import tr.havelsan.ueransim.mts.TypeRegistry;
import tr.havelsan.ueransim.nas.core.NasValue;
import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.core.ies.InformationElement;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.eap.Eap;
import tr.havelsan.ueransim.nas.eap.EapAkaPrime;
import tr.havelsan.ueransim.nas.eap.EapIdentity;
import tr.havelsan.ueransim.nas.eap.EapNotification;
import tr.havelsan.ueransim.sctp.SCTPClient;
import tr.havelsan.ueransim.utils.*;
import tr.havelsan.ueransim.utils.bits.BitN;
import tr.havelsan.ueransim.utils.octets.OctetN;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.function.Function;

public class UeRanSim {
    private static String flowPath;
    private static boolean dryRun;

    public static void main(String[] args) throws IOException {
        initLog();
        initMts();
        initWithArgs(args);

        SimulationFlow flow;
        try {
            flow = getSimulationFlow(flowPath);
            flowControl(flow);

            if (dryRun) {
                Console.println(Color.GREEN, "[SUCCESS] Dry run completed, flow json file is valid.");
                return;
            }

            Environment.AMF_HOST = flow.setup.amfHost;
            Environment.AMF_PORT = flow.setup.amfPort;

            Console.println();

            var sctpClient = new SCTPClient(Environment.AMF_HOST, Environment.AMF_PORT, Constants.NGAP_PROTOCOL_ID);
            sctpClient.start();
            new UeRanSimFlow(sctpClient, flow).start();
            sctpClient.close();
        } catch (MtsException | EncodingException | DecodingException | IncorrectImplementationException e) {
            Console.println(Color.RED, "[ERROR] %s - %s", e.getClass().getSimpleName(), e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            Console.println(Color.RED, "[ERROR] Exception raised.");
            Console.println(Color.RED, Utils.stackTraceString(e));
            System.exit(1);
        }
    }

    private static void initWithArgs(String[] args) {
        if (args.length >= 1) {
            flowPath = args[0];
        } else {
            printUsage();
            System.exit(1);
        }

        if (Arrays.asList(args).contains("-d")) {
            dryRun = true;
        }

        if (Arrays.asList(args).contains("-p")) {
            dumping();
            System.exit(0);
            return;
        }

        if (Arrays.stream(args).anyMatch(s -> s.startsWith("-") && !s.equals("-d"))) {
            System.out.println("unrecognized options");
            printUsage();
            System.exit(1);
        }
    }

    private static void printUsage() {
        System.out.println("usage: ueransim [filepath] {-d} {-p}");
        System.out.println("-d\tDry run");
        System.out.println("-p\tPrint type information");
    }

    private static void initLog() throws IOException {
        var logFile = String.format("sim-%s.log",
                new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
        System.out.println("(logging at file " + new File(logFile).getAbsolutePath() + ")");
        System.out.println();

        var writer = new FileWriter(logFile, true);

        Console.addPrintHandler(string -> {
            for (var item : Color.values()) {
                string = string.replace(item.val, "");
            }

            try {
                writer.write(string);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        });
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

                //Console.print(Color.RED, typeName + " ");
                //Console.println(Color.BLUE, clazz.getName());

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
                SimulationFlow.class,
                FlowConfig.class,
                SimulationStep.class,
                SimulatorSetup.class,
        };

        for (var type : otherTypes) {
            TypeRegistry.registerTypeName(type.getSimpleName(), type);
        }

        TypeRegistry.registerCustomType(new MtsProtocolEnumRegistry());
        TypeRegistry.registerCustomType(new MtsIEEapMessage());
        TypeRegistry.registerCustomType(new MtsEapAkaAttributes());

        MtsDecoder.setFileProvider((searchDir, path) -> {
            try {
                return Files.readString(Paths.get(searchDir, path));
            } catch (NoSuchFileException e) {
                throw new MtsException("file not found: %s", e.getFile());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static SimulationFlow getSimulationFlow(String fileName) {
        var decoded = MtsDecoder.decode(fileName);
        if (decoded instanceof ImplicitTypedObject) {
            return MtsConstruct.construct(SimulationFlow.class, ((ImplicitTypedObject) decoded).getParameters(), true);
        } else if (decoded instanceof SimulationFlow) {
            return (SimulationFlow) decoded;
        } else {
            String name = decoded == null ? "null" : decoded.getClass().getSimpleName();
            throw new MtsException("unexpected type: '%s', SimulationFlow is expected", name);
        }
    }

    private static void flowControl(SimulationFlow flow) {
        Function<String, Boolean> isValidIpAddress = ip -> {
            try {
                if (ip == null || ip.isEmpty()) return false;
                String[] parts = ip.split("\\.");
                if (parts.length != 4) return false;
                for (String s : parts) {
                    int i = Integer.parseInt(s);
                    if ((i < 0) || (i > 255)) return false;
                }
                return !ip.endsWith(".");
            } catch (NumberFormatException ignored) {
                return false;
            }
        };

        Fun nullControl = () -> {
            var list = new ArrayList<String>();
            Utils.findNullPublicFields(flow, list);
            for (var item : list) {
                throw new MtsException("missing mandatory property: %s", item);
            }
        };

        Fun setupControl = () -> {
            if (flow.setup.amfPort < 1024 || flow.setup.amfPort > 65535)
                throw new MtsException("%s value must be in range [1024, 65535]", "setup.amfPort");
            if (!isValidIpAddress.apply(flow.setup.amfHost))
                throw new MtsException("invalid ip address: %s", flow.setup.amfHost);
        };

        Fun configControl = () -> {
            if (flow.config.ranUeNgapId <= 0)
                throw new MtsException("%s value must be positive", "config.ranUeNgapId");
            if (flow.config.amfUeNgapId <= 0)
                throw new MtsException("%s value must be positive", "config.amfUeNgapId");
        };

        Fun stepsControl = () -> {
            if (flow.steps.length == 0)
                throw new MtsException("at least one step is required");

            int index = 0;
            for (var step : flow.steps) {
                if (step.nasMessage == null)
                    throw new MtsException("missing mandatory property: steps[%d].nasMessage", index);
                if (step.ngapType == null)
                    throw new MtsException("missing mandatory property: steps[%d].ngapType", index);
                index++;
            }
        };

        Fun onReceiveControl = () -> {
            int index = 0;
            for (var item : flow.passOnReceive) {
                if (item == null) {
                    throw new MtsException("passOnReceive[%d] is null", index);
                }
                var type = TypeRegistry.getClassByName(item);
                if (type == null) {
                    throw new MtsException("registered type not found: %s, at passOnReceive[%d]", item, index);
                }
                if (!NasMessage.class.isAssignableFrom(type)) {
                    throw new MtsException("specified type is not a NasMessage: %s, at passOnReceive[%d]", item, index);
                }
                index++;
            }

            index = 0;
            for (var item : flow.failOnReceive) {
                if (item == null) {
                    throw new MtsException("failOnReceive[%d] is null", index);
                }
                var type = TypeRegistry.getClassByName(item);
                if (type == null) {
                    throw new MtsException("registered type not found: %s, at failOnReceive[%d]", item, index);
                }
                if (!NasMessage.class.isAssignableFrom(type)) {
                    throw new MtsException("specified type is not a NasMessage: %s, at failOnReceive[%d]", item, index);
                }
                index++;
            }

            var set = new HashSet<String>();
            set.addAll(Arrays.asList(flow.failOnReceive));
            set.addAll(Arrays.asList(flow.passOnReceive));
            if (set.size() != flow.failOnReceive.length + flow.passOnReceive.length) {
                throw new MtsException("duplicate items found at passOnReceive and/or failOnReceive");
            }
        };

        Funs.run(nullControl)
                .then(setupControl)
                .then(configControl)
                .then(stepsControl)
                .then(onReceiveControl)
                .invoke();
    }

    private static void dumping() {
        var messages = hierarchyTypeDumping(NasMessage.class);
        var ies = hierarchyTypeDumping(InformationElement.class);
        var enums = hierarchyEnumDumping(ProtocolEnum.class);
        var values = hierarchyTypeDumping(NasValue.class);
        var coreTypes = hierarchyTypeDumping(BitN.class, OctetN.class, OctetString.class);
        var others = hierarchyTypeDumping(BitN.class, OctetN.class, OctetString.class);

        var dump = new JsonObject();
        dump.add("messages", messages);
        dump.add("informationElements", ies);
        dump.add("enums", enums);
        dump.add("values", values);
        dump.add("coreTypes", coreTypes);
        dump.add("others", others);

        Console.println(Color.CYAN_BRIGHT, Json.toJson(dump));
    }

    private static JsonObject typeDumping(Class<?> type) {
        var message = new JsonObject();

        /*var fields = new JsonObject();
        Arrays.stream(type.getDeclaredFields())
                .filter(field -> Modifier.isPublic(field.getModifiers()))
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .forEach(field -> {
                    var name = field.getName();
                    var typeName = TypeRegistry.getClassName(field.getType());
                    if (typeName == null) {
                        typeName = field.getType().getSimpleName();
                    }
                    fields.add(name, new JsonPrimitive(typeName));
                });*/

        var constructors = new JsonArray();
        Arrays.stream(type.getDeclaredConstructors())
                .filter(constructor -> Modifier.isPublic(constructor.getModifiers()))
                .forEach(constructor -> {
                    var ctor = new JsonObject();
                    Arrays.stream(constructor.getParameters())
                            .forEach(parameter -> {
                                var name = parameter.getName();
                                var typeName = TypeRegistry.getClassName(parameter.getType());
                                if (typeName == null) {
                                    typeName = parameter.getType().getSimpleName();
                                }
                                ctor.add(name, new JsonPrimitive(typeName));
                            });
                    if (ctor.size() > 0)
                        constructors.add(ctor);
                });

        var subTypes = TypeRegistry.getClassesAssignableTo(type);
        if (subTypes.size() > 1) {
            var arr = new JsonArray();

            for (var subType : subTypes) {
                if (subType.equals(type))
                    continue;

                var typeName = TypeRegistry.getClassName(subType);
                if (typeName == null)
                    typeName = subType.getSimpleName();

                arr.add(new JsonPrimitive(typeName));
            }

            message.add("subTypes", arr);
        }

        //message.add("fields", fields);
        message.add("constructors", constructors);

        return message;
    }

    private static JsonObject hierarchyTypeDumping(Class<?>... assignableTo) {
        var obj = new JsonObject();
        var types = TypeRegistry.getClassesAssignableTo(assignableTo);
        for (var type : types)
            obj.add(TypeRegistry.getClassName(type), typeDumping(type));
        return obj;
    }

    private static JsonObject hierarchyEnumDumping(Class<?> assignableTo) {
        var obj = new JsonObject();
        var types = TypeRegistry.getClassesAssignableTo(assignableTo);
        for (var type : types) {
            obj.add(TypeRegistry.getClassName(type), enumDumping((Class<? extends ProtocolEnum>) type));
        }
        return obj;
    }

    private static JsonArray enumDumping(Class<? extends ProtocolEnum> type) {
        var arr = new JsonArray();

        Arrays.stream(type.getDeclaredFields())
                .filter(field -> Modifier.isPublic(field.getModifiers()))
                .filter(field -> Modifier.isStatic(field.getModifiers()))
                .filter(field -> field.getType().equals(type))
                .forEach(field -> {
                    Object val;
                    try {
                        val = field.get(null);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }

                    var obj = new JsonObject();
                    obj.add("identifier", new JsonPrimitive(field.getName()));
                    obj.add("value", new JsonPrimitive(((ProtocolEnum) val).intValue()));
                    obj.add("name", new JsonPrimitive(((ProtocolEnum) val).name()));

                    arr.add(obj);
                });

        return arr;
    }
}
