/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_EUTRA_PhysCellId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RSRP_Range;

public class RRC_MeasResultSFTD_EUTRA extends AsnSequence {
    public RRC_EUTRA_PhysCellId eutra_PhysCellId; // mandatory
    public AsnInteger sfn_OffsetResult; // mandatory, VALUE(0..1023)
    public AsnInteger frameBoundaryOffsetResult; // mandatory, VALUE(-30720..30719)
    public RRC_RSRP_Range rsrp_Result; // optional
}

