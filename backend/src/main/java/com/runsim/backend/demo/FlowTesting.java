package com.runsim.backend.demo;

import com.runsim.backend.flows.RegistrationFlow;
import com.runsim.backend.nas.NasPduParser;
import com.runsim.backend.utils.Utils;

public class FlowTesting {
    public static void main(String[] args) throws Exception {

        var data = Utils.hexStringToByteArray("7E004171000D010011000000000000000000F12E0480808080");

        var pdu = new NasPduParser(data).parseNasMessage();

        if (1 == 1) {
            return;
        }

        /*var plain = new PlainNASMessage();
        plain.setExtendedProtocolDiscriminator(new ExtendedProtocolDiscriminator(ExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES));
        plain.setSecurityHeaderType(new SecurityHeaderType(SecurityHeaderType.INTEGRITY_PROTECTED_AND_CIPHERED));
        System.err.println(plain);*/

        var flow = new RegistrationFlow();
        flow.start();
    }
}
