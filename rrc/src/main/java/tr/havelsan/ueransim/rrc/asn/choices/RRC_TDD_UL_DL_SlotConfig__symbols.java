/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Null;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_TDD_UL_DL_SlotConfig__symbols__explicit;

public class RRC_TDD_UL_DL_SlotConfig__symbols extends RRC_Choice {

    public RRC_Null allDownlink;
    public RRC_Null allUplink;
    public RRC_TDD_UL_DL_SlotConfig__symbols__explicit Explicit;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "allDownlink","allUplink","explicit" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "allDownlink","allUplink","Explicit" };
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
