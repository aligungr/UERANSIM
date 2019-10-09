package com.runsim.backend;

import com.runsim.backend.annotations.StateMachine;
import com.runsim.backend.web.SessionManager;
import io.javalin.Javalin;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class App {

    private static HashMap<String, Class> stateMachineTypes;

    public static void main(String[] args) {
        findStateMachines();
        startServer();
    }

    private static void findStateMachines() {
        stateMachineTypes = new HashMap<>();
        var reflections = new Reflections(Constants.MACHINE_PREFIX);
        var types = reflections.getTypesAnnotatedWith(StateMachine.class);
        for (var type : types) {
            String name = type.getName();
            String shortName = name.substring(Constants.MACHINE_PREFIX.length() + 1);
            stateMachineTypes.put(shortName, type);
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

    public static Set<String> getMachineNames() {
        return stateMachineTypes.keySet();
    }
}
