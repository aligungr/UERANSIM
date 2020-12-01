/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ControlResourceSetZero;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SearchSpaceZero;

public class RRC_PDCCH_ConfigSIB1 extends RRC_Sequence {

    public RRC_ControlResourceSetZero controlResourceSetZero;
    public RRC_SearchSpaceZero searchSpaceZero;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "controlResourceSetZero","searchSpaceZero" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "controlResourceSetZero","searchSpaceZero" };
    }

    @Override
    public String getAsnName() {
        return "PDCCH-ConfigSIB1";
    }

    @Override
    public String getXmlTagName() {
        return "PDCCH-ConfigSIB1";
    }

}
