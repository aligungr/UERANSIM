package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainSmMessage;
import com.runsim.backend.nas.impl.enums.EExtendedProtocolDiscriminator;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.enums.EPduSessionIdentity;
import com.runsim.backend.nas.impl.enums.EProcedureTransactionIdentity;

public class PduSessionEstablishmentRequest extends PlainSmMessage {

    public PduSessionEstablishmentRequest() {
        super.extendedProtocolDiscriminator = EExtendedProtocolDiscriminator.SESSION_MANAGEMENT_MESSAGES;
        super.pduSessionId = EPduSessionIdentity.NO_VAL;
        super.pti = EProcedureTransactionIdentity.NO_VAL;
        super.messageType = EMessageType.PDU_SESSION_ESTABLISHMENT_REQUEST;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        throw new NotImplementedException("");
    }
}
