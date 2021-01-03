/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.impl.enums.EMccValue;
import tr.havelsan.ueransim.nas.impl.enums.EMncValue;
import tr.havelsan.ueransim.nas.impl.values.V5gTmsi;
import tr.havelsan.ueransim.nas.impl.values.VAmfSetId;
import tr.havelsan.ueransim.nas.impl.values.VPlmn;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.bits.Bit6;
import tr.havelsan.ueransim.utils.octets.Octet;

public class IE5gGutiMobileIdentity extends IE5gsMobileIdentity {
    public EMccValue mcc;
    public EMncValue mnc;
    public Octet amfRegionId;
    public VAmfSetId amfSetId;
    public Bit6 amfPointer;
    public V5gTmsi tmsi;

    public IE5gGutiMobileIdentity() {
    }

    public IE5gGutiMobileIdentity(EMccValue mcc, EMncValue mnc, Octet amfRegionId, VAmfSetId amfSetId, Bit6 amfPointer, V5gTmsi tmsi) {
        this.mcc = mcc;
        this.mnc = mnc;
        this.amfRegionId = amfRegionId;
        this.amfSetId = amfSetId;
        this.amfPointer = amfPointer;
        this.tmsi = tmsi;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        stream.writeOctet(0xf2); // Flags for 5G-GUTI

        /* Encode MCC and MNC*/
        var mccmnc = new VPlmn();
        mccmnc.mcc = mcc;
        mccmnc.mnc = mnc;
        mccmnc.encode(stream);

        /* Encode region id */
        stream.writeOctet(amfRegionId);

        /* Encode AMF set id and AMF pointer */
        var octets = amfSetId.toIntArray();
        octets[1] |= amfPointer.intValue();
        stream.writeOctets(octets);

        /* Encode TMSI */
        tmsi.encode(stream);
    }
}
