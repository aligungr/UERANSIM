/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_PH_UplinkCarrierSCG extends RRC_Sequence {

    public RRC_Integer ph_Type1or3;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ph-Type1or3" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ph_Type1or3" };
    }

    @Override
    public String getAsnName() {
        return "PH-UplinkCarrierSCG";
    }

    @Override
    public String getXmlTagName() {
        return "PH-UplinkCarrierSCG";
    }

}
