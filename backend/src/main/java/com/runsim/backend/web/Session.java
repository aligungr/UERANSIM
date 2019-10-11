package com.runsim.backend.web;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.runsim.backend.App;

import java.util.UUID;

public class Session {
    private UUID connectionId;
    private ISender sender;

    public Session(UUID connectionId, ISender sender) {
        this.connectionId = connectionId;
        this.sender = sender;
    }

    private static JsonObject makeMessage(String type, JsonElement data) {
        var mes = new JsonObject();
        mes.add("type", new JsonPrimitive(type));
        mes.add("data", data);
        return mes;
    }

    private static JsonObject makeMessage(String type, String data) {
        return makeMessage(type, new JsonPrimitive(data));
    }

    public void errorResponse(String message) {
        sender.send("errorResponse", message);
    }

    @Command
    public void getAllFlows() {
        sender.send("allFlows", App.getMachineNames());
    }
}
