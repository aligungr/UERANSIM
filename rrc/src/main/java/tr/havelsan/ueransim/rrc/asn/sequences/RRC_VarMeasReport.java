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
import tr.havelsan.ueransim.rrc.asn.integers.RRC_MeasId;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CellsTriggeredList;

public class RRC_VarMeasReport extends AsnSequence {
    public RRC_MeasId measId; // mandatory
    public RRC_CellsTriggeredList cellsTriggeredList; // optional
    public AsnInteger numberOfReportsSent; // mandatory
}

