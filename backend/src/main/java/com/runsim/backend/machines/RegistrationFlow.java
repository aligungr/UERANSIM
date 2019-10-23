package com.runsim.backend.machines;

import com.runsim.backend.Action;
import com.runsim.backend.MachineContext;
import com.runsim.backend.MessageContext;
import com.runsim.backend.annotations.Starter;
import com.runsim.backend.annotations.State;
import com.runsim.backend.annotations.StateMachine;

@StateMachine
public class RegistrationFlow {

    @Starter
    public Action starter(MachineContext machineContext) {
        return Action.sendData(new byte[]{
                (byte) 0x00, (byte) 0x0f, (byte) 0x40, (byte) 0x3f, (byte) 0x00, (byte) 0x00, (byte) 0x04, (byte) 0x00, (byte) 0x55, (byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x26, (byte) 0x00, (byte) 0x1a, (byte) 0x19, (byte) 0x7e, (byte) 0x00, (byte) 0x41, (byte) 0x71, (byte) 0x00, (byte) 0x0d, (byte) 0x01, (byte) 0x00, (byte) 0x11, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xf1, (byte) 0x2e, (byte) 0x04, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x00, (byte) 0x79, (byte) 0x00, (byte) 0x0f, (byte) 0x40, (byte) 0x00, (byte) 0x01, (byte) 0x10, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x10, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x10, (byte) 0x00, (byte) 0x00, (byte) 0x75, (byte) 0x00, (byte) 0x5a, (byte) 0x40, (byte) 0x01, (byte) 0x18
        }, "state1");
    }

    @State
    public Action state1(MachineContext machineContext, MessageContext messageContext) {
        return Action.noOperation();
    }
}
