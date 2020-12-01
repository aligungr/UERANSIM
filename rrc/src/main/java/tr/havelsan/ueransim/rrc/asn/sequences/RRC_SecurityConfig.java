/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_SecurityConfig extends RRC_Sequence {

    public RRC_SecurityAlgorithmConfig securityAlgorithmConfig;
    public RRC_Integer keyToUse;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "securityAlgorithmConfig","keyToUse" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "securityAlgorithmConfig","keyToUse" };
    }

    @Override
    public String getAsnName() {
        return "SecurityConfig";
    }

    @Override
    public String getXmlTagName() {
        return "SecurityConfig";
    }

}
