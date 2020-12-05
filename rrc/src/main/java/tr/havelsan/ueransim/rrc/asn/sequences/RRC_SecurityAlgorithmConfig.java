package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_CipheringAlgorithm;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_IntegrityProtAlgorithm;

public class RRC_SecurityAlgorithmConfig extends AsnSequence {
    public RRC_CipheringAlgorithm cipheringAlgorithm; // mandatory
    public RRC_IntegrityProtAlgorithm integrityProtAlgorithm; // optional
}

