/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_ModulationOrder extends AsnEnumerated {
    public static final RRC_ModulationOrder BPSK_HALFPI = new RRC_ModulationOrder(0);
    public static final RRC_ModulationOrder BPSK = new RRC_ModulationOrder(1);
    public static final RRC_ModulationOrder QPSK = new RRC_ModulationOrder(2);
    public static final RRC_ModulationOrder QAM16 = new RRC_ModulationOrder(3);
    public static final RRC_ModulationOrder QAM64 = new RRC_ModulationOrder(4);
    public static final RRC_ModulationOrder QAM256 = new RRC_ModulationOrder(5);

    private RRC_ModulationOrder(long value) {
        super(value);
    }
}

