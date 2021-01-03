/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.impl.values.V5gTmsi;
import tr.havelsan.ueransim.nas.impl.values.VAmfSetId;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.bits.Bit6;

public class IE5gTmsiMobileIdentity extends IE5gsMobileIdentity {
    public VAmfSetId amfSetId;
    public Bit6 amfPointer;
    public V5gTmsi tmsi;

    public IE5gTmsiMobileIdentity() {
    }

    public IE5gTmsiMobileIdentity(VAmfSetId amfSetId, Bit6 amfPointer, V5gTmsi tmsi) {
        this.amfSetId = amfSetId;
        this.amfPointer = amfPointer;
        this.tmsi = tmsi;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        stream.writeOctet(0b11110100); // Flags for 5G-TMSI

        /* Encode AMF set id and AMF pointer */
        var octets = amfSetId.toIntArray();
        octets[1] |= amfPointer.intValue();
        stream.writeOctets(octets);

        /* Encode TMSI */
        tmsi.encode(stream);
    }
}
