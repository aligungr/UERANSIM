/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_FilterCoefficient;

public class RRC_FilterConfig extends AsnSequence {
    public RRC_FilterCoefficient filterCoefficientRSRP; // optional
    public RRC_FilterCoefficient filterCoefficientRSRQ; // optional
    public RRC_FilterCoefficient filterCoefficientRS_SINR; // optional
}

