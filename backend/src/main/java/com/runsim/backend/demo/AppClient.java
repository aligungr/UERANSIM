package com.runsim.backend.demo;

import com.runsim.backend.*;
import com.runsim.backend.annotations.Starter;
import com.runsim.backend.annotations.State;
import com.runsim.backend.otn.*;

public class AppClient {

    public static class TestMachine extends StateMachine {

        @Starter
        public Action starter(MachineContext machineContext) {
            OtnElement obj = OtnXmlParser.parseXml(Utils.getResourceString("initialUEMessage.xml"));
            return Action.sendElement("NGAP_PDU_Contents.InitialUEMessage", obj, "someState");
            //return Action.sendData(Utils.hexStringToByteArray("000f403f0000040055000200010026001a197e004171000d010011000000000000000000f12e04808080800079000f400001100000011000000110000075005a400118"), "someState");
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
