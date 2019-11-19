package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class ERetransmissionOfInitialNasMessageRequest extends ProtocolEnum {
    public static final ERetransmissionOfInitialNasMessageRequest NOT_REQUESTED
            = new ERetransmissionOfInitialNasMessageRequest(0b0, "Retransmission of the initial NAS message not requested");
    public static final ERetransmissionOfInitialNasMessageRequest REQUESTED
            = new ERetransmissionOfInitialNasMessageRequest(0b1, "Retransmission of the initial NAS message requested");

    private ERetransmissionOfInitialNasMessageRequest(int value, String name) {
        super(value, name);
    }

    public static ERetransmissionOfInitialNasMessageRequest fromValue(int value) {
        return fromValueGeneric(ERetransmissionOfInitialNasMessageRequest.class, value);
    }
}
