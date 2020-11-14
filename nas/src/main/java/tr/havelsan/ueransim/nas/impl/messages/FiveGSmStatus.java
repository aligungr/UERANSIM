/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.messages;

import tr.havelsan.ueransim.nas.core.IMessageBuilder;
import tr.havelsan.ueransim.nas.core.messages.PlainSmMessage;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.ies.IE5gSmCause;

public class FiveGSmStatus extends PlainSmMessage {
    public IE5gSmCause smCause;

    public FiveGSmStatus() {
        super(EMessageType.FIVEG_SM_STATUS);
    }

    public FiveGSmStatus(IE5gSmCause smCause) {
        this();
        this.smCause = smCause;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE("smCause");
    }
}
