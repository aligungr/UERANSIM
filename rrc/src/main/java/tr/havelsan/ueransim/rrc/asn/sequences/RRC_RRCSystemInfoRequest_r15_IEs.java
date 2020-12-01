/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_RRCSystemInfoRequest_r15_IEs extends RRC_Sequence {

    public RRC_BitString requested_SI_List;
    public RRC_BitString spare;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "requested-SI-List","spare" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "requested_SI_List","spare" };
    }

    @Override
    public String getAsnName() {
        return "RRCSystemInfoRequest-r15-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "RRCSystemInfoRequest-r15-IEs";
    }

}
