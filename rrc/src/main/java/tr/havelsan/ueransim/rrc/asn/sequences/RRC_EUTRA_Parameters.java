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
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FreqBandIndicatorEUTRA;

public class RRC_EUTRA_Parameters extends AsnSequence {
    public RRC_supportedBandListEUTRA supportedBandListEUTRA; // mandatory, SIZE(1..256)
    public RRC_EUTRA_ParametersCommon eutra_ParametersCommon; // optional
    public RRC_EUTRA_ParametersXDD_Diff eutra_ParametersXDD_Diff; // optional

    // SIZE(1..256)
    public static class RRC_supportedBandListEUTRA extends AsnSequenceOf<RRC_FreqBandIndicatorEUTRA> {
    }
}

