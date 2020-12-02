/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;

public class RRC_CSI_RS_Resource_Mobility__frequencyDomainAllocation extends RRC_Choice {

    public RRC_BitString row1;
    public RRC_BitString row2;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "row1","row2" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "row1","row2" };
    }

}
