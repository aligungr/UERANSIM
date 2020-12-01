/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_UAC_BarringInfoSet extends RRC_Sequence {

    public RRC_Integer uac_BarringFactor;
    public RRC_Integer uac_BarringTime;
    public RRC_BitString uac_BarringForAccessIdentity;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "uac-BarringFactor","uac-BarringTime","uac-BarringForAccessIdentity" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "uac_BarringFactor","uac_BarringTime","uac_BarringForAccessIdentity" };
    }

    @Override
    public String getAsnName() {
        return "UAC-BarringInfoSet";
    }

    @Override
    public String getXmlTagName() {
        return "UAC-BarringInfoSet";
    }

}
