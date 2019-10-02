package com.runsim.backend.machine.stateresult;

import com.runsim.backend.machine.StateMachine;

public class SwitchToMachine extends StateResult {
    private final StateMachine stateMachine;

    public SwitchToMachine(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    public StateMachine getStateMachine() {
        return stateMachine;
    }
}
