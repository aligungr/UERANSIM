package tr.havelsan.ueransim.app.app;

import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;
import tr.havelsan.ueransim.app.common.sw.SwStep;
import tr.havelsan.ueransim.utils.Json;
import tr.havelsan.ueransim.utils.Severity;

public class StepperMessagingListener implements INodeMessagingListener {

    private void onMessage(BaseSimContext ctx, Object message) {
        SwStep swStep = new SwStep(AppConfig.generateNodeName(ctx), Severity.DEBUG, message.getClass().getSimpleName(), Json.toJson(message));
    }

    @Override
    public void onSend(BaseSimContext ctx, Object message) {
        onMessage(ctx, message);
    }

    @Override
    public void onReceive(BaseSimContext ctx, Object message) {
        onMessage(ctx, message);
    }
}
