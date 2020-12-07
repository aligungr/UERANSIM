/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBoolean;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_MeasReportQuantity extends AsnSequence {
    public AsnBoolean rsrp; // mandatory
    public AsnBoolean rsrq; // mandatory
    public AsnBoolean sinr; // mandatory
}

