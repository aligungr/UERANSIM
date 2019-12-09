package com.runsim.backend.app;

import com.runsim.backend.app.sim.*;
import com.runsim.backend.exceptions.MtsException;
import com.runsim.backend.mts.ImplicitTypedObject;
import com.runsim.backend.mts.MtsConstruct;
import com.runsim.backend.mts.MtsDecoder;
import com.runsim.backend.mts.TypeRegistry;
import com.runsim.backend.nas.eap.*;
import com.runsim.backend.utils.Fun;
import com.runsim.backend.utils.Funs;
import com.runsim.backend.utils.Utils;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;

import java.util.ArrayList;
import java.util.function.Function;

public class RunSim {

    public static void main(String[] args) throws Exception {
        initMts();

        var flow = getSimulationFlow("test1/flow1.json");
        flowControl(flow);

        Constants.AMF_HOST = flow.setup.amfHost;
        Constants.AMF_PORT = flow.setup.amfPort;

        new RunSimFlow(flow).start();
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

        MtsDecoder.setFileProvider(Utils::getResourceString);
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

        Funs.run(nullControl)
                .then(setupControl)
                .then(configControl)
                .then(stepsControl)
                .invoke();
    }
}
