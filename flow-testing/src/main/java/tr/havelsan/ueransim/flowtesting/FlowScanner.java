package tr.havelsan.ueransim.flowtesting;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import java.util.Locale;
import tr.havelsan.ueransim.core.Constants;
import tr.havelsan.ueransim.sim.BaseFlow;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Set;

public class FlowScanner {

    private static HashMap<String, Class<? extends BaseFlow>> flowTypes;
    private static HashMap<String, Class<? extends BaseFlow>> flowTypesLower;

    private static void findFlowTypes() {
        flowTypes = new HashMap<>();
        flowTypesLower = new HashMap<>();
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
                flowTypesLower.put(clazz.getSimpleName().toLowerCase(Locale.ENGLISH), clazz);
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
        return flowTypesLower.get(flowName.toLowerCase(Locale.ENGLISH));
    }
}
