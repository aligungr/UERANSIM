/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_P_Max;

public class RRC_ConfigRestrictModReqSCG extends RRC_Sequence {

    public RRC_BandCombinationInfoSN requestedBC_MRDC;
    public RRC_P_Max requestedP_MaxFR1;
    public RRC_ConfigRestrictModReqSCG__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "requestedBC-MRDC","requestedP-MaxFR1","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "requestedBC_MRDC","requestedP_MaxFR1","ext1" };
    }

    @Override
    public String getAsnName() {
        return "ConfigRestrictModReqSCG";
    }

    @Override
    public String getXmlTagName() {
        return "ConfigRestrictModReqSCG";
    }

}
