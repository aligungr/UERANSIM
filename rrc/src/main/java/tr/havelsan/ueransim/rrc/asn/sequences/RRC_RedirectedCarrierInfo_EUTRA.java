/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueEUTRA;

public class RRC_RedirectedCarrierInfo_EUTRA extends RRC_Sequence {

    public RRC_ARFCN_ValueEUTRA eutraFrequency;
    public RRC_Integer cnType;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "eutraFrequency","cnType" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "eutraFrequency","cnType" };
    }

    @Override
    public String getAsnName() {
        return "RedirectedCarrierInfo-EUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "RedirectedCarrierInfo-EUTRA";
    }

}
