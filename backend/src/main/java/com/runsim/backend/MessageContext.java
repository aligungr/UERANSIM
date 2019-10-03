package com.runsim.backend;

public class MessageContext {
    private final byte[] receivedData;
    private final int streamNumber;

    public MessageContext(byte[] receivedData, int streamNumber) {
        this.receivedData = receivedData;
        this.streamNumber = streamNumber;
    }

    public byte[] getReceivedData() {
        return receivedData;
    }

    public int getStreamNumber() {
        return streamNumber;
    }
}
