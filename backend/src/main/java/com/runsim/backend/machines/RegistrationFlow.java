package com.runsim.backend.machines;

import com.runsim.backend.Action;
import com.runsim.backend.MachineContext;
import com.runsim.backend.MessageContext;
import com.runsim.backend.annotations.Starter;
import com.runsim.backend.annotations.State;
import com.runsim.backend.annotations.StateMachine;
import com.runsim.backend.otn.OtnElement;
import com.runsim.backend.otn.OtnXmlParser;
import com.runsim.backend.utils.Utils;

@StateMachine
public class RegistrationFlow {

    @Starter
    public Action starter(MachineContext machineContext) {
        return Action.closeConnection();
    }
}
