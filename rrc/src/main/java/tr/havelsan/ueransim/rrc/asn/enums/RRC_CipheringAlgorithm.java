/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_CipheringAlgorithm extends AsnEnumerated {
    public static final RRC_CipheringAlgorithm NEA0 = new RRC_CipheringAlgorithm(0);
    public static final RRC_CipheringAlgorithm NEA1 = new RRC_CipheringAlgorithm(1);
    public static final RRC_CipheringAlgorithm NEA2 = new RRC_CipheringAlgorithm(2);
    public static final RRC_CipheringAlgorithm NEA3 = new RRC_CipheringAlgorithm(3);
    public static final RRC_CipheringAlgorithm SPARE4 = new RRC_CipheringAlgorithm(4);
    public static final RRC_CipheringAlgorithm SPARE3 = new RRC_CipheringAlgorithm(5);
    public static final RRC_CipheringAlgorithm SPARE2 = new RRC_CipheringAlgorithm(6);
    public static final RRC_CipheringAlgorithm SPARE1 = new RRC_CipheringAlgorithm(7);

    private RRC_CipheringAlgorithm(long value) {
        super(value);
    }
}

