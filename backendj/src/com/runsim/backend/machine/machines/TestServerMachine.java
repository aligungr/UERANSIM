package com.runsim.backend.machine.machines;

import com.runsim.backend.machine.StateMachine;
import com.runsim.backend.machine.handleresult.SendData;
import com.runsim.backend.machine.stateresult.SendMessage;
import com.runsim.backend.machine.stateresult.StateResult;

public class TestServerMachine extends StateMachine {

    @Override
    public StateResult initialState(byte[] input) {
        return new SendMessage(new byte[]{1,2,3,4}, null);
    }
}
