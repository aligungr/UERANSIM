/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_PagingUE_Identity;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_PagingRecord extends RRC_Sequence {

    public RRC_PagingUE_Identity ue_Identity;
    public RRC_Integer accessType;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ue-Identity","accessType" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ue_Identity","accessType" };
    }

    @Override
    public String getAsnName() {
        return "PagingRecord";
    }

    @Override
    public String getXmlTagName() {
        return "PagingRecord";
    }

}
