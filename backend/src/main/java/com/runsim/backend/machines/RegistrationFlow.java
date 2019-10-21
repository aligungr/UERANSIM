package com.runsim.backend.machines;

import com.runsim.backend.Action;
import com.runsim.backend.MachineContext;
import com.runsim.backend.MessageContext;
import com.runsim.backend.annotations.Starter;
import com.runsim.backend.annotations.State;
import com.runsim.backend.annotations.StateMachine;
import com.runsim.backend.mts.MTSAdapter;
import com.runsim.backend.otn.OtnXmlParser;
import com.runsim.backend.utils.Utils;

import java.util.function.Function;

@StateMachine
public class RegistrationFlow {

    @Starter
    public Action starter(MachineContext machineContext) {
        return Action.sendElement(
                "NGAP_PDU_Descriptions.InitiatingMessage",
                OtnXmlParser.parseXml(Utils.getResourceString("initialUEMessage.xml")),
                "waitForRequest"
        );
    }

    @State
    public Action waitForRequest(MachineContext machineContext, MessageContext messageContext) {
//        var type = parse(msgCtx.getReceivedData());
        return Action.switchState(messageContext, "send");
    }
//
//    @State
//    public Action sendIdentityResponse(MessageContext msgCtx, MachineContext machineCtx) {
//        return Action.sendElement("IdentityResp", Identity_Resp, "waitForRequest");
//    }

//    @State
//    public Action sendAuthenticationResponse(MessageContext msgCtx, MachineContext machineCtx) {
//        return Action.sendElement()
//    }
//
//    @State
//    public Action sendSecurityModeComplete(MessageContext msgCtx, MachineContext machineCtx) {
//
//    }
//
//    @State
//    public Action sendContextSetupResponse(MessageContext msgCtx, MachineContext machineCtx) {
//
//    }

//    private String parse(byte[] msg) {
//        var elem = MTSAdapter.decodeAper(msg);
//        elem.
//    }
}
