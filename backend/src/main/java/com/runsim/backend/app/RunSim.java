package com.runsim.backend.app;

import com.runsim.backend.app.sim.*;
import com.runsim.backend.exceptions.MtsException;
import com.runsim.backend.mts.ImplicitTypedObject;
import com.runsim.backend.mts.MtsConstruct;
import com.runsim.backend.mts.MtsDecoder;
import com.runsim.backend.mts.TypeRegistry;
import com.runsim.backend.nas.core.messages.NasMessage;
import com.runsim.backend.nas.eap.*;
import com.runsim.backend.utils.*;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.function.Function;

public class RunSim {
    private static String flowPath;
    private static boolean dryRun;

    public static void main(String[] args) throws IOException {
        initWithArgs(args);
        initLog();
        initMts();

        var flow = getSimulationFlow(flowPath);
        flowControl(flow);

        if (dryRun) {
            Console.println(Color.GREEN, "[SUCCESS] flow json file is valid.");
            return;
        }

        Constants.AMF_HOST = flow.setup.amfHost;
        Constants.AMF_PORT = flow.setup.amfPort;

        try {
            Console.println();
            new RunSimFlow(flow).start();
        } catch (Exception e) {
            Console.println(Color.RED, "[ERROR] Exception raised.");
            Console.println(Color.RED, Utils.stackTraceString(e));
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

        if (Arrays.stream(args).anyMatch(s -> s.startsWith("-") && !s.equals("-d"))) {
            System.out.println("unrecognized options");
            printUsage();
            System.exit(1);
        }
    }

    private static void printUsage() {
        System.out.println("usage: runsim [filepath] {-d}");
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
                EEapAkaAttributeType.class,
                EEapAkaSubType.class,
                EEapCode.class,
                EEapType.class,
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

        MtsDecoder.setFileProvider(path -> {
            try {
                return Files.readString(Paths.get(path));
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
}
