package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.utils.OctetOutputStream;

import java.nio.charset.StandardCharsets;

public class IENsiMobileIdentity extends IESuciMobileIdentity {
    public String suciNai;

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        stream.writeOctet(0b00010001); // Flags for SUCI with SUPI format NSI
        stream.writeOctets(suciNai.getBytes(StandardCharsets.UTF_8));
    }
}
