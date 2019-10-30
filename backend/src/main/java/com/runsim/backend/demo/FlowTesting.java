package com.runsim.backend.demo;

import com.runsim.backend.nas.impl.ExtendedProtocolDiscriminator;
import com.runsim.backend.nas.impl.PlainNASMessage;
import com.runsim.backend.nas.impl.SecurityHeaderType;

public class FlowTesting {
    public static void main(String[] args) throws Exception {

        var plain = new PlainNASMessage();
        plain.setExtendedProtocolDiscriminator(new ExtendedProtocolDiscriminator(ExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES));
        plain.setSecurityHeaderType(new SecurityHeaderType(SecurityHeaderType.INTEGRITY_PROTECTED_AND_CIPHERED));
        System.err.println(plain);

        //var flow = new RegistrationFlow();
        //flow.start();
    }
}
