package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainMmMessage;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.ies.IE5gMmCause;
import com.runsim.backend.nas.impl.ies.IEEapMessage;
import com.runsim.backend.nas.impl.ies.IEGprsTimer2;

public class RegistrationReject extends PlainMmMessage {
    public IE5gMmCause mmCause;
    public IEGprsTimer2 t3346value;
    public IEGprsTimer2 t3502value;
    public IEEapMessage eapMessage;

    public RegistrationReject() {
        super(EMessageType.REGISTRATION_REJECT);
    }

    public RegistrationReject(IE5gMmCause mmCause, IEGprsTimer2 t3346value, IEGprsTimer2 t3502value, IEEapMessage eapMessage) {
        this();
        this.mmCause = mmCause;
        this.t3346value = t3346value;
        this.t3502value = t3502value;
        this.eapMessage = eapMessage;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE("mmCause");
        builder.optionalIE(0x5F, "t3346value");
        builder.optionalIE(0x16, "t3502value");
        builder.optionalIE(0x78, "eapMessage");
    }
}
