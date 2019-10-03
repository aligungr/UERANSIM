package com.runsim.backend;

import com.runsim.backend.machine.*;
import com.runsim.backend.machine.annotations.Starter;
import com.runsim.backend.machine.annotations.State;

class TestMachine extends StateMachine {

    @Starter
    public Action starter(MachineContext machineContext) {
        return Action.sendData(new byte[12], "someState");
    }

    @State
    public Action someState(MessageContext messageContext, MachineContext machineContext) {
        return Action.closeConnection();
    }
}


public class AppClient {

    public static void main(String[] args) throws Exception {
        MachineController<TestMachine> controller = new MachineController<>(new TestMachine(),
                Constants.HOST, Constants.PORT);
        controller.run();
    }
}
