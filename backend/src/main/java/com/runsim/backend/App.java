package com.runsim.backend;

import com.runsim.backend.web.Session;
import com.runsim.backend.web.SessionManager;
import io.javalin.Javalin;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class App {

    private static HashMap<String, Class<? extends BaseFlow>> flowTypes;

    public static void main(String[] args) {
        //compilerOptionsControl();
        findFlowTypes();
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
        var reflections = new Reflections(Constants.MACHINE_PREFIX);
        var types = reflections.getTypesAnnotatedWith(BaseFlow.Flow.class);
        for (var type : types) {
            String name = type.getName();
            String shortName = name.substring(Constants.MACHINE_PREFIX.length() + 1);
            flowTypes.put(shortName, (Class<? extends BaseFlow>) type);
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
        return flowTypes.keySet();
    }

    public static Class<? extends BaseFlow> getFlowType(String flowName) {
        return flowTypes.get(flowName);
    }
}
