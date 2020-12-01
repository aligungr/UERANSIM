/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_AS_Config__ext1 extends RRC_Sequence {

    public RRC_OctetString sourceRB_SN_Config;
    public RRC_OctetString sourceSCG_NR_Config;
    public RRC_OctetString sourceSCG_EUTRA_Config;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "sourceRB-SN-Config","sourceSCG-NR-Config","sourceSCG-EUTRA-Config" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "sourceRB_SN_Config","sourceSCG_NR_Config","sourceSCG_EUTRA_Config" };
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
