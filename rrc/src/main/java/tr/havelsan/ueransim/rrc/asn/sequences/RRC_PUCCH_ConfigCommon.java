/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_PUCCH_ConfigCommon extends RRC_Sequence {

    public RRC_Integer pucch_ResourceCommon;
    public RRC_Integer pucch_GroupHopping;
    public RRC_Integer hoppingId;
    public RRC_Integer p0_nominal;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "pucch-ResourceCommon","pucch-GroupHopping","hoppingId","p0-nominal" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "pucch_ResourceCommon","pucch_GroupHopping","hoppingId","p0_nominal" };
    }

    @Override
    public String getAsnName() {
        return "PUCCH-ConfigCommon";
    }

    @Override
    public String getXmlTagName() {
        return "PUCCH-ConfigCommon";
    }

}
