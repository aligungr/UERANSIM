/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_PUCCH_TPC_CommandConfig extends AsnSequence {
    public AsnInteger tpc_IndexPCell; // optional, VALUE(1..15)
    public AsnInteger tpc_IndexPUCCH_SCell; // optional, VALUE(1..15)
}

