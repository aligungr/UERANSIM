package tr.havelsan.ueransim.app;

import tr.havelsan.ueransim.core.Constants;
import tr.havelsan.ueransim.web.Session;
import tr.havelsan.ueransim.web.SessionManager;
import tr.havelsan.ueransim.BaseFlow;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import io.javalin.Javalin;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class Backend {

    private static HashMap<String, Class<? extends BaseFlow>> flowTypes;

    public static void main(String[] args) throws Exception {
        //compilerOptionsControl();
        startServer();
    }

    private static void compilerOptionsControl() {
        for (var method : Session.class.getMethods()) {
            for (var parameter : method.getParameters()) {
                if (!parameter.isNamePresent())
                    throw new IllegalArgumentException("Parameter names are not present!");
            }
        }
    }

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

    private static void startServer() {
        var app = Javalin.create().start(Constants.BACKEND_PORT);

        app.ws("/", ws -> {
            ws.onConnect(ctx -> {
                var uuid = UUID.randomUUID();
                ctx.attribute("connection-id", uuid);
                SessionManager.onConnect(uuid, ctx::send);
            });
            ws.onError(ctx -> {
                var uuid = (UUID) ctx.attribute("connection-id");
                SessionManager.onError(uuid, ctx.error());
            });
            ws.onMessage(ctx -> {
                var uuid = (UUID) ctx.attribute("connection-id");
                SessionManager.onMessage(uuid, ctx.message());
            });
            ws.onClose(ctx -> {
                var uuid = (UUID) ctx.attribute("connection-id");
                SessionManager.onClose(uuid);
            });
        });
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
