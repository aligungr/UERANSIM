/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_MIMO_LayersUL extends AsnEnumerated {
    public static final RRC_MIMO_LayersUL ONELAYER = new RRC_MIMO_LayersUL(0);
    public static final RRC_MIMO_LayersUL TWOLAYERS = new RRC_MIMO_LayersUL(1);
    public static final RRC_MIMO_LayersUL FOURLAYERS = new RRC_MIMO_LayersUL(2);

    private RRC_MIMO_LayersUL(long value) {
        super(value);
    }
}

