package com.runsim.backend.flows;

import com.runsim.backend.BaseFlow;
import com.runsim.backend.Message;
import com.runsim.backend.NGAP;
import com.runsim.backend.ngap.ngap_pdu_contents.DownlinkNASTransport;
import com.runsim.backend.ngap.ngap_pdu_descriptions.InitiatingMessage;
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

        if (!(pdu.getValue() instanceof InitiatingMessage)) {
            System.err.println("bad message, InitiatingMessage is expected");
            return this::waitingRequests;
        }

        var initiatingMessage = (InitiatingMessage) pdu.getValue();

        if (!(initiatingMessage.value.getDecodedValue() instanceof DownlinkNASTransport)) {
            System.err.println("bad message, DownlinkNASTransport is expected");
            return this::waitingRequests;
        }

        var downlinkNASTransport = (DownlinkNASTransport) initiatingMessage.value.getDecodedValue();

        return closeConnection();
    }
}
