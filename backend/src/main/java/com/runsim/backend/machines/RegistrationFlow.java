package com.runsim.backend.machines;

import com.runsim.backend.Action;
import com.runsim.backend.MachineContext;
import com.runsim.backend.annotations.Starter;
import com.runsim.backend.annotations.StateMachine;

@StateMachine
public class RegistrationFlow {

    @Starter
    public Action starter(MachineContext machineContext) {
        return Action.closeConnection();
    }
}
