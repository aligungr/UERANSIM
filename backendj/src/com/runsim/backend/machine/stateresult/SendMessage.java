package com.runsim.backend.machine.stateresult;

public class SendMessage extends StateResult {
    private final byte[] data;
    private final String nextState;

    public SendMessage(byte[] data, String nextState) {
        this.data = data;
        this.nextState = nextState;
    }

    public byte[] getData() {
        return data;
    }

    public String getNextState() {
        return nextState;
    }
}
