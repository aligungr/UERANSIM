/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_S_NSSAI;
import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;

public class RRC_RRCSetupComplete_IEs__s_NSSAI_List extends RRC_SequenceOf<RRC_S_NSSAI> {

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

    @Override
    public Class<RRC_S_NSSAI> getItemType() {
        return RRC_S_NSSAI.class;
    }

}
