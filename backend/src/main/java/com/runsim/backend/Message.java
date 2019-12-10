package com.runsim.backend;

import com.runsim.backend.ngap.ngap_pdu_descriptions.NGAP_PDU;

public class Message {
    private final byte[] rawData;
    private final int length;
    private final int streamNumber;

    public Message(byte[] rawData, int streamNumber) {
        this.rawData = rawData;
        this.length = rawData.length;
        this.streamNumber = streamNumber;
    }

    public byte[] getRawData() {
        return rawData;
    }

    public int getLength() {
        return length;
    }

    public int getStreamNumber() {
        return streamNumber;
    }

    public NGAP_PDU getAsPDU() {
        return Ngap.perDecode(NGAP_PDU.class, getRawData());
    }
}
