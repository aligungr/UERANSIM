/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_CipheringAlgorithm;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_IntegrityProtAlgorithm;

public class RRC_SecurityAlgorithmConfig extends AsnSequence {
    public RRC_CipheringAlgorithm cipheringAlgorithm; // mandatory
    public RRC_IntegrityProtAlgorithm integrityProtAlgorithm; // optional
}

