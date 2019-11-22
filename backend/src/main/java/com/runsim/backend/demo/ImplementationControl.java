package com.runsim.backend.demo;

import com.runsim.backend.Constants;
import com.runsim.backend.exceptions.IncorrectImplementationException;
import com.runsim.backend.nas.core.ProtocolEnum;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;

import java.util.function.Consumer;

// TODO
public class ImplementationControl {

    public static void main(String[] args) throws Exception {
        controlMessages();
        controlInformationElements();
        controlProtocolEnums();
        controlNasValues();
    }

    private static ClassGraph makeClassGraph() {
        return new ClassGraph().enableClassInfo().ignoreClassVisibility().whitelistPackages(Constants.NAS_IMPL_PREFIX);
    }

    private static <T> void controlForType(Class<T> type, Consumer<Class> control) throws Exception {
        try (ScanResult scanResult = makeClassGraph().scan()) {
            var classInfoList = scanResult.getAllClasses();
            if (classInfoList.size() == 0)
                throw new IncorrectImplementationException("no class found for type: " + type);

            for (var classInfo : classInfoList) {
                Class<?> clazz;
                try {
                    clazz = Class.forName(classInfo.getName());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                if (type.isAssignableFrom(clazz)) {
                    control.accept(clazz);
                }
            }
        }
    }

    private static void controlProtocolEnums() throws Exception {
        controlForType(ProtocolEnum.class, clazz -> {
            //
        });
    }

    private static void controlNasValues() {

    }

    private static void controlInformationElements() {

    }

    private static void controlMessages() {

    }
}
