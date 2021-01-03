/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_IntegrityProtAlgorithm extends AsnEnumerated {
    public static final RRC_IntegrityProtAlgorithm NIA0 = new RRC_IntegrityProtAlgorithm(0);
    public static final RRC_IntegrityProtAlgorithm NIA1 = new RRC_IntegrityProtAlgorithm(1);
    public static final RRC_IntegrityProtAlgorithm NIA2 = new RRC_IntegrityProtAlgorithm(2);
    public static final RRC_IntegrityProtAlgorithm NIA3 = new RRC_IntegrityProtAlgorithm(3);
    public static final RRC_IntegrityProtAlgorithm SPARE4 = new RRC_IntegrityProtAlgorithm(4);
    public static final RRC_IntegrityProtAlgorithm SPARE3 = new RRC_IntegrityProtAlgorithm(5);
    public static final RRC_IntegrityProtAlgorithm SPARE2 = new RRC_IntegrityProtAlgorithm(6);
    public static final RRC_IntegrityProtAlgorithm SPARE1 = new RRC_IntegrityProtAlgorithm(7);

    private RRC_IntegrityProtAlgorithm(long value) {
        super(value);
    }
}

