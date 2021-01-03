/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;

public class RRC_MRDC_AssistanceInfo extends AsnSequence {
    public RRC_affectedCarrierFreqCombInfoListMRDC affectedCarrierFreqCombInfoListMRDC; // mandatory, SIZE(1..128)

    // SIZE(1..128)
    public static class RRC_affectedCarrierFreqCombInfoListMRDC extends AsnSequenceOf<RRC_AffectedCarrierFreqCombInfoMRDC> {
    }
}

