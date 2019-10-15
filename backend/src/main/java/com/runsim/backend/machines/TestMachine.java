package com.runsim.backend.machines;

import com.runsim.backend.Action;
import com.runsim.backend.MachineContext;
import com.runsim.backend.MessageContext;
import com.runsim.backend.annotations.StateMachine;
import com.runsim.backend.annotations.Starter;
import com.runsim.backend.annotations.State;
import com.runsim.backend.otn.OtnElement;
import com.runsim.backend.otn.OtnXmlParser;
import com.runsim.backend.utils.Utils;

@StateMachine
public class TestMachine {

    @Starter
    public Action starter(MachineContext machineContext) {
        int age = 22;



        OtnElement obj = OtnXmlParser.parseXml(Utils.getResourceString("initialUEMessage.xml"));
        return Action.sendElement("NGAP_PDU_Contents.InitialUEMessage", obj, "someState");
        //return Action.sendData(Utils.hexStringToByteArray("000f403f0000040055000200010026001a197e004171000d010011000000000000000000f12e04808080800079000f400001100000011000000110000075005a400118"), "someState");
    }

    @State
    public Action someState(MessageContext messageContext, MachineContext machineContext) {
        String name = "ali";

        return Action.closeConnection();
    }
}