package com.runsim.backend.web;

import com.google.gson.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

public class SessionManager {
    private static ConcurrentHashMap<UUID, Session> sessions = new ConcurrentHashMap<>();

    public static void onConnect(UUID uuid, Consumer<String> sender) {
        sessions.put(uuid, new Session(uuid, message -> {
            sender.accept(new Gson().toJson(message));
        }));
    }

    public static void onClose(UUID uuid) {
        sessions.remove(uuid);
    }

    public static void onError(UUID uuid, Throwable error) {

    }

    public static void onMessage(UUID uuid, String message) {
        var session = sessions.get(uuid);

        JsonObject msg;
        try {
            msg = JsonParser.parseString(message).getAsJsonObject();
        } catch (Exception e) {
            session.errorIndication(e.getMessage());
            return;
        }

        JsonElement cmdElement = msg.get("cmd");

        msg.getAsString();

        if (cmdElement == null || cmdElement.isJsonNull()) {
            session.errorIndication("cmd expected");
            return;
        }

        if (!cmdElement.isJsonPrimitive() || !cmdElement.getAsJsonPrimitive().isString()) {
            session.errorIndication("cmd expected");
            return;
        }
    }
}
