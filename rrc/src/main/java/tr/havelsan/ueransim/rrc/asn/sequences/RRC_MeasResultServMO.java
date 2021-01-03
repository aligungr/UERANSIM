/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_MeasResultServMO extends AsnSequence {
    public RRC_ServCellIndex servCellId; // mandatory
    public RRC_MeasResultNR measResultServingCell; // mandatory
    public RRC_MeasResultNR measResultBestNeighCell; // optional
}

