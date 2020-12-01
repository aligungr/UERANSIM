/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_RRCReestablishmentRequest extends RRC_Sequence {

    public RRC_RRCReestablishmentRequest_IEs rrcReestablishmentRequest;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "rrcReestablishmentRequest" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "rrcReestablishmentRequest" };
    }

    @Override
    public String getAsnName() {
        return "RRCReestablishmentRequest";
    }

    @Override
    public String getXmlTagName() {
        return "RRCReestablishmentRequest";
    }

}
