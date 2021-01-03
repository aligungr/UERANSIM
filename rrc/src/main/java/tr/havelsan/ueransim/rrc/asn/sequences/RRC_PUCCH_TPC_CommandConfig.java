/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_PUCCH_TPC_CommandConfig extends AsnSequence {
    public AsnInteger tpc_IndexPCell; // optional, VALUE(1..15)
    public AsnInteger tpc_IndexPUCCH_SCell; // optional, VALUE(1..15)
}

