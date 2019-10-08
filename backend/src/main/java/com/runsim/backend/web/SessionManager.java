package com.runsim.backend.web;

import com.google.gson.*;
import com.runsim.backend.json.JElement;
import com.runsim.backend.json.JObject;
import com.runsim.backend.json.Json;

import javax.management.RuntimeMBeanException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Function;

public class SessionManager {
    private static ConcurrentHashMap<UUID, Session> sessions = new ConcurrentHashMap<>();

    public static void onConnect(UUID uuid, Consumer<String> sender) {
        sessions.put(uuid, new Session(uuid, message -> {
            sender.accept(Json.toJson(message));
        }));
    }

    public static void onClose(UUID uuid) {
        sessions.remove(uuid);
    }

    public static void onError(UUID uuid, Throwable error) {
        String message = error == null ? "n/a" : error.getMessage();
        var session = sessions.get(uuid);
        session.errorIndication(message);
    }

    public static void onMessage(UUID uuid, String message) throws InvocationTargetException, IllegalAccessException {
        var session = sessions.get(uuid);

        // Json syntax controls
        var obj = JObject.parse(message);
        if (obj == null) {
            session.errorIndication("Expected JSON Object, {cmd: <cmdname>, args: {}}");
            return;
        }
        var cmd = obj.getString("cmd");
        var args = obj.getObject("args");

        if (cmd == null) {
            session.errorIndication("cmd is required");
            return;
        }
        if (args == null) {
            session.errorIndication("args is required.");
            return;
        }

        // Find method
        Method method;
        try {
            method = session.getClass().getMethod(cmd);
        } catch (Exception e) {
            session.errorIndication("cmd not found: " + cmd);
            return;
        }

        if (method == null || !method.isAnnotationPresent(Command.class)) {
            session.errorIndication("cmd not found: " + cmd);
            return;
        }

        var params = new Object[method.getParameterCount()];
        for (int i = 0; i < method.getParameterCount(); i++) {
            var param = method.getParameters()[i];
            var json = args.get(param.getName());
            if (json == null) {
                session.errorIndication("args must contain: " + param.getName());
                return;
            }
            Object arg;
            try {
                arg = Json.fromJson(json.toString(), param.getType());
            } catch (Exception e) {
                session.errorIndication("unable to parse");
                return;
            }
            params[i] = arg;
        }

        method.invoke(session, params);
    }
}
