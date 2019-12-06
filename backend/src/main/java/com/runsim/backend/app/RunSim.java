package com.runsim.backend.app;

import com.runsim.backend.Constants;
import com.runsim.backend.app.sim.*;
import com.runsim.backend.mts.MtsDecoder;
import com.runsim.backend.mts.TypeRegistry;
import com.runsim.backend.nas.eap.*;
import com.runsim.backend.utils.Console;
import com.runsim.backend.utils.Utils;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;

public class RunSim {

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
                SimulationConfig.class,
                SimulationStep.class,
        };

        for (var type : otherTypes) {
            TypeRegistry.registerTypeName(type.getSimpleName(), type);
        }

        TypeRegistry.registerCustomType(new MtsProtocolEnumRegistry());
        TypeRegistry.registerCustomType(new MtsIEEapMessage());
        TypeRegistry.registerCustomType(new MtsEapAkaAttributes());

        MtsDecoder.setFileProvider(Utils::getResourceString);
    }

    public static void main(String[] args) {
        initMts();

        var nasMessage = MtsDecoder.decode("flow1.json");
        Console.println(Json.toJson(nasMessage));
    }
}
