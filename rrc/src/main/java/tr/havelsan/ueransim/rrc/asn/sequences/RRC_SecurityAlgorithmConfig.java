/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_CipheringAlgorithm;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_IntegrityProtAlgorithm;

public class RRC_SecurityAlgorithmConfig extends RRC_Sequence {

    public RRC_CipheringAlgorithm cipheringAlgorithm;
    public RRC_IntegrityProtAlgorithm integrityProtAlgorithm;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "cipheringAlgorithm","integrityProtAlgorithm" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "cipheringAlgorithm","integrityProtAlgorithm" };
    }

    @Override
    public String getAsnName() {
        return "SecurityAlgorithmConfig";
    }

    @Override
    public String getXmlTagName() {
        return "SecurityAlgorithmConfig";
    }

}
