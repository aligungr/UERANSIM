package com.runsim.backend.web;

import com.google.gson.*;
import com.runsim.backend.utils.Fun;
import com.runsim.backend.utils.Funs;

import java.lang.reflect.Method;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class SessionManager {
    private static ConcurrentHashMap<UUID, Session> sessions = new ConcurrentHashMap<>();

    public static void onConnect(UUID connectionId, Consumer<String> sender) {
        sessions.put(connectionId, new Session(connectionId, (type, data) -> {
            var mes = new JsonObject();
            mes.add("type", new JsonPrimitive(type));
            mes.add("data", JsonParser.parseString(new Gson().toJson(data)));
            sender.accept(mes.toString());
        }));
    }

    public static void onClose(UUID connectionId) {
        sessions.remove(connectionId);
    }

    public static void onError(UUID connectionId, Throwable error) {
        var message = error == null ? "n/a" : error.getMessage();
        var session = sessions.get(connectionId);
        session.errorIndication(message);
    }

    public static void onMessage(UUID connectionId, String message) {
        var session = sessions.get(connectionId);

        var ref = new Object() {
            String cmd;
            JsonObject args;
            Method method;
            Object[] params;
        };

        Fun jsonSyntaxControls = () -> {
            var element = JsonParser.parseString(message);
            if (!element.isJsonObject())
                throw new RuntimeException("json object expected as message");
            var obj = element.getAsJsonObject();
            var cmdElement = obj.get("cmd");
            var argsElement = obj.get("args");

            if (cmdElement == null || !cmdElement.isJsonPrimitive() || !cmdElement.getAsJsonPrimitive().isString())
                throw new RuntimeException("string field is expected: 'cmd'");
            if (argsElement == null || !argsElement.isJsonObject())
                throw new RuntimeException("object field is expected: 'args'");

            ref.cmd = cmdElement.getAsString();
            ref.args = argsElement.getAsJsonObject();
        };

        Fun commandControls = () -> {
            Method method = null;
            var allMethods = session.getClass().getMethods(); // TODO: Check for multiple methods with same name!
            for (var m : allMethods) {
                if (m.getName().equals(ref.cmd))
                    method = m;
            }
            if (method == null || !method.isAnnotationPresent(Command.class))
                throw new RuntimeException("cmd not found: " + ref.cmd);
            ref.method = method;
            ref.params = new Object[ref.method.getParameterCount()];
        };

        Fun parameterControls = () -> {
            for (int i = 0; i < ref.method.getParameterCount(); i++) {
                var param = ref.method.getParameters()[i];
                var json = ref.args.get(param.getName());
                if (json == null)
                    throw new RuntimeException("args must contain: " + param.getName());
                var arg = new Gson().fromJson(json.toString(), param.getType());
                ref.params[i] = arg;
            }
        };

        Fun invokeMethod = () -> {
            try {
                ref.method.invoke(session, ref.params);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

        Funs.run(jsonSyntaxControls, e -> session.errorIndication(e.getMessage()))
                .then(commandControls, e -> session.errorIndication("command not found: " + ref.cmd))
                .then(parameterControls, e -> session.errorIndication("unable to parse args, " + e.getMessage()))
                .then(invokeMethod, e -> session.errorIndication("cmd method invocation failed, " + e.getMessage()))
                .invoke();
    }
}
