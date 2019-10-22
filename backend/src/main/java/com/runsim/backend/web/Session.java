package com.runsim.backend.web;

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

    public void errorResponse(String message) {
        sender.send("errorResponse", message);
    }

    @Command
    public void allFlows() {
        sender.send("allFlows", App.getMachineNames());
    }

    @Command
    public void flowSetup(String flowName) {
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

        sender.send("flowSetup", machineController.getMachineInfo());
    }
}
