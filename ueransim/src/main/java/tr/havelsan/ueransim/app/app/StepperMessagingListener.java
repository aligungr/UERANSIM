package tr.havelsan.ueransim.app.app;

import io.javalin.websocket.WsConnectContext;
import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;
import tr.havelsan.ueransim.app.common.sw.SwStep;
import tr.havelsan.ueransim.app.utils.SocketWrapperSerializer;
import tr.havelsan.ueransim.utils.Json;
import tr.havelsan.ueransim.utils.Severity;

public class StepperMessagingListener implements INodeMessagingListener {

    private WsConnectContext ws;

    private void onMessage(BaseSimContext ctx, Object message) {
        if (message == null) {
            return;
        }

        var loggerName = AppConfig.generateNodeName(ctx);
        var severity = Severity.DEBUG; // TODO
        var messageName = message.getClass().getSimpleName();
        var messageBody = Json.toJson(message);

        var swStep = new SwStep(loggerName, severity, messageName, messageBody);

        if (ws != null)
            ws.send(SocketWrapperSerializer.toJson(swStep));
    }

    @Override
    public void onSend(BaseSimContext ctx, Object message) {
        onMessage(ctx, message);
    }

    @Override
    public void onReceive(BaseSimContext ctx, Object message) {
        onMessage(ctx, message);
    }

    public void onConnect(WsConnectContext ctx) {
        this.ws = ctx;
    }
}
