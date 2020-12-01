/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SRS_PeriodicityAndOffset;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_SRS_Resource__resourceType__periodic extends RRC_Sequence {

    public RRC_SRS_PeriodicityAndOffset periodicityAndOffset_p;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "periodicityAndOffset-p" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "periodicityAndOffset_p" };
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
