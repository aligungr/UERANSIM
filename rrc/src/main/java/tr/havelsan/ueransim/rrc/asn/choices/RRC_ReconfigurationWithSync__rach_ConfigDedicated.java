/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RACH_ConfigDedicated;

public class RRC_ReconfigurationWithSync__rach_ConfigDedicated extends RRC_Choice {

    public RRC_RACH_ConfigDedicated uplink;
    public RRC_RACH_ConfigDedicated supplementaryUplink;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "uplink","supplementaryUplink" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "uplink","supplementaryUplink" };
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
