/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_RACH_ConfigCommon__groupBconfigured extends RRC_Sequence {

    public RRC_Integer ra_Msg3SizeGroupA;
    public RRC_Integer messagePowerOffsetGroupB;
    public RRC_Integer numberOfRA_PreamblesGroupA;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ra-Msg3SizeGroupA","messagePowerOffsetGroupB","numberOfRA-PreamblesGroupA" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ra_Msg3SizeGroupA","messagePowerOffsetGroupB","numberOfRA_PreamblesGroupA" };
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

}
