package com.runsim.backend.flows;

import com.runsim.backend.BaseFlow;
import com.runsim.backend.Message;
import com.runsim.backend.NGAP;
import com.runsim.backend.utils.Utils;

public class RegistrationFlow extends BaseFlow {

    public State main(Message message) {
        sendPDU(Utils.getResourceString("InitialUEMessage.xml"));
        return this::waitingRequests;
    }

    private State waitingRequests(Message message) {
        var pdu = message.getAsPDU();

        System.out.println("---------------");
        System.out.println("message received " + message.getLength() + " bytes.");
        System.out.println(NGAP.xerEncode(pdu));
        System.out.println("---------------");

        return closeConnection();
    }
}
