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
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MultiFrequencyBandListNR_SIB;

public class RRC_FrequencyInfoDL_SIB extends AsnSequence {
    public RRC_MultiFrequencyBandListNR_SIB frequencyBandList; // mandatory
    public AsnInteger offsetToPointA; // mandatory, VALUE(0..2199)
    public RRC_scs_SpecificCarrierList_2 scs_SpecificCarrierList; // mandatory, SIZE(1..5)

    // SIZE(1..5)
    public static class RRC_scs_SpecificCarrierList_2 extends AsnSequenceOf<RRC_SCS_SpecificCarrier> {
    }
}

