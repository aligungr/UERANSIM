package com.runsim.backend.machine.handleresult;

public class SendData extends MessageHandleResult {
    private final byte[] data;

    public SendData(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }
}
