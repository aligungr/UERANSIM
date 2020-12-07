/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_MeasResultServMO extends AsnSequence {
    public RRC_ServCellIndex servCellId; // mandatory
    public RRC_MeasResultNR measResultServingCell; // mandatory
    public RRC_MeasResultNR measResultBestNeighCell; // optional
}

