package tr.havelsan.ueransim.nas.impl.messages;

import tr.havelsan.ueransim.nas.core.IMessageBuilder;
import tr.havelsan.ueransim.nas.core.messages.PlainMmMessage;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.ies.IEAccessType;

public class Notification extends PlainMmMessage {
    public IEAccessType accessType;

    public Notification() {
        super(EMessageType.NOTIFICATION);
    }

    public Notification(IEAccessType accessType) {
        this();
        this.accessType = accessType;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE1("accessType");
    }
}
