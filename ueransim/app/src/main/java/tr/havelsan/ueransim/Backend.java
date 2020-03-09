package tr.havelsan.ueransim;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import tr.havelsan.ueransim.core.Constants;
import tr.havelsan.ueransim.sim.BaseFlow;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Set;

public class Backend {

    private static HashMap<String, Class<? extends BaseFlow>> flowTypes;

    private static void findFlowTypes() {
        flowTypes = new HashMap<>();
        try (ScanResult scanResult = new ClassGraph().enableClassInfo().ignoreClassVisibility().whitelistPackages(Constants.FLOWS_PREFIX).scan()) {
            var classInfoList = scanResult.getAllClasses();
            for (var classInfo : classInfoList) {
                Class clazz;
                try {
                    clazz = Class.forName(classInfo.getName());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                if (!BaseFlow.class.isAssignableFrom(clazz)) continue;
                if (Modifier.isAbstract(clazz.getModifiers()) || clazz.isInterface()) continue;
                flowTypes.put(clazz.getSimpleName(), clazz);
            }
        }
    }

    public static Set<String> getFlowNames() {
        if (flowTypes == null) {
            findFlowTypes();
        }
        return flowTypes.keySet();
    }

    public static Class<? extends BaseFlow> getFlowType(String flowName) {
        if (flowTypes == null) {
            findFlowTypes();
        }
        return flowTypes.get(flowName);
    }
}
