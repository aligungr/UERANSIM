package com.runsim.backend.web;

import java.util.UUID;

public class Session {
    private UUID connectionId;
    private ISender sender;

    public Session(UUID connectionId, ISender sender) {
        this.connectionId = connectionId;
        this.sender = sender;
    }

    public void errorIndication(String message) {
        sender.send(message);
    }

    @Command
    public void hello(String name) {
        sender.send("Hello " + name);
    }
}
