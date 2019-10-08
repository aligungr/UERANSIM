package com.runsim.backend;

import com.runsim.backend.web.SessionManager;
import io.javalin.Javalin;

import java.util.UUID;
import java.util.function.Consumer;

public class App {

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(Constants.BACKEND_PORT);

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
}
