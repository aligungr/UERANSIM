package com.runsim.backend.flows;

import com.runsim.backend.BaseFlow;
import com.runsim.backend.Message;
import com.runsim.backend.NGAP;
import com.runsim.backend.ngap.ngap_pdu_descriptions.NGAP_PDU;
import com.runsim.backend.utils.Utils;

import java.io.ByteArrayInputStream;

public class RegistrationFlow extends BaseFlow {

    public State main(Message message) throws Exception {
        new NGAP_PDU().perDecode(NGAP.getContext(), new ByteArrayInputStream(
                Utils.hexStringToByteArray("000f403f0000040055000200010026001a197e004171000d010011000000000000000000f12e04808080800079000f400001100000011000000110000075005a400118")
        ));

        //sendBase16("000f403f0000040055000200010026001a197e004171000d010011000000000000000000f12e04808080800079000f400001100000011000000110000075005a400118");
        return this::waitingRequests;
    }

    private State waitingRequests(Message message) {
        System.out.println("---------------");
        System.out.println("message received " + message.getLength() + " bytes.");
        System.out.println("---------------");
        String mes = Utils.byteArrayToHexString(message.getRawData());

        return closeConnection();
    }
}
