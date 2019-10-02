package com.runsim.backend.machine.stateresult;

public class NoOperation {
    private final String nextState;

    public NoOperation(String nextState) {
        this.nextState = nextState;
    }

    public String getNextState() {
        return nextState;
    }
}
