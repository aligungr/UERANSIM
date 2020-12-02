/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Null;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_ControlResourceSet__cce_REG_MappingType__interleaved;

public class RRC_ControlResourceSet__cce_REG_MappingType extends RRC_Choice {

    public RRC_ControlResourceSet__cce_REG_MappingType__interleaved interleaved;
    public RRC_Null nonInterleaved;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "interleaved","nonInterleaved" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "interleaved","nonInterleaved" };
    }

}
