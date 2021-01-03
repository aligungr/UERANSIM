/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_CellReselectionSubPriority extends AsnEnumerated {
    public static final RRC_CellReselectionSubPriority ODOT2 = new RRC_CellReselectionSubPriority(0);
    public static final RRC_CellReselectionSubPriority ODOT4 = new RRC_CellReselectionSubPriority(1);
    public static final RRC_CellReselectionSubPriority ODOT6 = new RRC_CellReselectionSubPriority(2);
    public static final RRC_CellReselectionSubPriority ODOT8 = new RRC_CellReselectionSubPriority(3);

    private RRC_CellReselectionSubPriority(long value) {
        super(value);
    }
}

