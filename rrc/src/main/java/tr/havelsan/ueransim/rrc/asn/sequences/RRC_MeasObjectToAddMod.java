/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_MeasObjectToAddMod__measObject;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_MeasObjectId;

public class RRC_MeasObjectToAddMod extends RRC_Sequence {

    public RRC_MeasObjectId measObjectId;
    public RRC_MeasObjectToAddMod__measObject measObject;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "measObjectId","measObject" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "measObjectId","measObject" };
    }

    @Override
    public String getAsnName() {
        return "MeasObjectToAddMod";
    }

    @Override
    public String getXmlTagName() {
        return "MeasObjectToAddMod";
    }

}
