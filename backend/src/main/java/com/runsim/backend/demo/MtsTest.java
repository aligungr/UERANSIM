package com.runsim.backend.demo;

import com.runsim.backend.Constants;
import com.runsim.backend.mts.MtsDecoder;
import com.runsim.backend.mts.TypeRegistry;
import com.runsim.backend.nas.mts.MtsProtocolEnumRegistry;
import com.runsim.backend.utils.Console;
import com.runsim.backend.utils.Json;
import com.runsim.backend.utils.Utils;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;

public class MtsTest {

    private static String getTypeName(Class<?> type) {
        if (type.getEnclosingClass() == null) {
            return type.getSimpleName();
        } else {
            return getTypeName(type.getEnclosingClass()) + "." + type.getSimpleName();
        }
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

                String typeName = getTypeName(clazz);

                //Console.print(Color.RED, typeName + " ");
                //Console.println(Color.BLUE, clazz.getName());

                TypeRegistry.registerTypeName(typeName, clazz);
            }
        }
        TypeRegistry.registerCustomType(new MtsProtocolEnumRegistry());
    }

    public static void main(String[] args) throws Exception {
        initMts();

        var jsonString = Utils.getResourceString("mts.json");

        var mtsDecoder = new MtsDecoder(false);
        var nasMessage = mtsDecoder.decode(jsonString);
        Console.println(Json.toJson(nasMessage));
    }
}
