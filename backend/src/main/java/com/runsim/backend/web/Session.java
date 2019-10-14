package com.runsim.backend.web;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.runsim.backend.App;
import com.runsim.backend.Constants;
import com.runsim.backend.MachineController;

import java.util.UUID;

public class Session {
    private UUID connectionId;
    private ISender sender;
    private MachineController machineController;

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

    @Command
    public void setupFlow(String flowName) {
        var type = App.getMachineType(flowName);
        if (type == null) {
            errorResponse("flow not found: " + flowName);
            return;
        }

        try {
            this.machineController = new MachineController(type, Constants.AMF_HOST, Constants.AMF_PORT);
        } catch (Exception e) {
            errorResponse("machine setup failed");
            return;
        }

        sender.send("machineSetup", machineController.getMachineInfo());
    }
}
