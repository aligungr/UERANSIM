package com.runsim.backend.machine.handleresult;

import com.runsim.backend.machine.StateMachine;

public class SwitchToMachine extends MessageHandleResult {
    private final StateMachine stateMachine;

    public SwitchToMachine(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    public StateMachine getStateMachine() {
        return stateMachine;
    }
}
