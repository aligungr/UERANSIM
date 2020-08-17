package tr.havelsan.ueransim.api.sys;

import tr.havelsan.ueransim.core.BaseSimContext;

public interface INodeMessagingListener {

    /**
     * Triggered when a simulation node has send a message.
     * WARNING: Do not mutate any of the parameters.
     */
    void onSend(BaseSimContext<?> ctx, Object message);

    /**
     * Triggered when a simulation node has recevied a message.
     * WARNING: Do not mutate any of the parameters.
     */
    void onReceive(BaseSimContext<?> ctx, Object message);
}
