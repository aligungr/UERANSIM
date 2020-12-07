/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_PUSCH_TPC_CommandConfig extends AsnSequence {
    public AsnInteger tpc_Index; // optional, VALUE(1..15)
    public AsnInteger tpc_IndexSUL; // optional, VALUE(1..15)
    public RRC_ServCellIndex targetCell; // optional
}

