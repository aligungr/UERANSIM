/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_InterRAT_Parameters extends RRC_Sequence {

    public RRC_EUTRA_Parameters eutra;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "eutra" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "eutra" };
    }

    @Override
    public String getAsnName() {
        return "InterRAT-Parameters";
    }

    @Override
    public String getXmlTagName() {
        return "InterRAT-Parameters";
    }

}
