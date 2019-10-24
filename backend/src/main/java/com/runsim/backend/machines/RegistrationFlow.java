package com.runsim.backend.machines;

import com.runsim.backend.Action;
import com.runsim.backend.MachineContext;
import com.runsim.backend.MessageContext;
import com.runsim.backend.annotations.Starter;
import com.runsim.backend.annotations.State;
import com.runsim.backend.annotations.StateMachine;
import com.runsim.backend.utils.Utils;

@StateMachine
public class RegistrationFlow {

    private static byte[] initiatingMes(String initialUEMessage) {
        byte[] ue = Utils.hexStringToByteArray(initialUEMessage);
        byte[] x = new byte[ue.length + 4];
        x[0] = 0x00;
        x[1] = 0x0F;
        x[2] = 0x40;
        x[3] = (byte) ue.length;
        System.arraycopy(ue, 0, x, 4, ue.length);
        return x;
    }


    @Starter
    public Action starter(MachineContext machineContext) {
        return Action.sendData(
                initiatingMes("00000400550002000500260021207e004171000d010011000000000099898877f71001002e04804080402f0201010079000f400001100000110000000110000075005a400118")
                , "state1"
        );
    }

    @State
    public Action state1(MachineContext machineContext, MessageContext messageContext) {
        return Action.noOperation();
    }
}
