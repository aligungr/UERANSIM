package com.runsim.backend.web;

import com.runsim.backend.App;
import com.runsim.backend.BaseFlow;

import java.util.UUID;

public class Session {
    private UUID connectionId;
    private ISender sender;
    private BaseFlow flow;

    public Session(UUID connectionId, ISender sender) {
        this.connectionId = connectionId;
        this.sender = sender;
    }

    public void errorResponse(String message) {
        sender.send("errorResponse", message);
    }

    @Command
    public void allFlows() {
        sender.send("allFlows", App.getFlowNames());
    }

    @Command
    public void flowSetup(String flowName) {
        var type = App.getFlowType(flowName);
        if (type == null) {
            errorResponse("flow not found: " + flowName);
            return;
        }

        try {
            this.flow = type.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // TODO
        //sender.send("flowSetup", this.flow.getFlowInfo());
    }
}
