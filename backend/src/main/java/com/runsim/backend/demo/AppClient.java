package com.runsim.backend.demo;

import com.runsim.backend.*;
import com.runsim.backend.annotations.Starter;
import com.runsim.backend.annotations.State;

public class AppClient {

    public static class TestMachine extends StateMachine {
        @Starter
        public Action starter(MachineContext machineContext) {
            return Action.sendData(new byte[12], "someState");
        }

        @State
        public Action someState(MessageContext messageContext, MachineContext machineContext) {
            return Action.closeConnection();
        }
    }

    public static void main(String[] args) throws Exception {
        MachineController controller = new MachineController(TestMachine.class, Constants.HOST, Constants.PORT);
        controller.run();
    }
}
