/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_MeasId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_MeasObjectId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ReportConfigId;

public class RRC_MeasIdToAddMod extends RRC_Sequence {

    public RRC_MeasId measId;
    public RRC_MeasObjectId measObjectId;
    public RRC_ReportConfigId reportConfigId;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "measId","measObjectId","reportConfigId" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "measId","measObjectId","reportConfigId" };
    }

    @Override
    public String getAsnName() {
        return "MeasIdToAddMod";
    }

    @Override
    public String getXmlTagName() {
        return "MeasIdToAddMod";
    }

}
