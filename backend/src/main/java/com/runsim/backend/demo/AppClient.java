package com.runsim.backend.demo;

import com.runsim.backend.*;
import com.runsim.backend.annotations.Starter;
import com.runsim.backend.annotations.State;
import com.runsim.backend.otn.OtnString;

public class AppClient {

    public static class TestMachine extends StateMachine {
        @Starter
        public Action starter(MachineContext machineContext) {
            return Action.sendElement("NGAP_CommonDataTypes.Criticality", new OtnString("ignore"), "someState");
        }

        @State
        public Action someState(MessageContext messageContext, MachineContext machineContext) {
            return Action.closeConnection();
        }
    }

    public static void main(String[] args) throws Exception {
        MachineController controller = new MachineController(TestMachine.class, Constants.AMF_HOST, Constants.AMF_PORT);
        controller.run();
    }
}
