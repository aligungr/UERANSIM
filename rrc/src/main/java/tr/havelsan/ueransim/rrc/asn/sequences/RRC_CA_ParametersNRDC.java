/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FeatureSetCombinationId;

public class RRC_CA_ParametersNRDC extends AsnSequence {
    public RRC_CA_ParametersNR ca_ParametersNR_ForDC; // optional
    public RRC_CA_ParametersNR_v1540 ca_ParametersNR_ForDC_v1540; // optional
    public RRC_CA_ParametersNR_v1550 ca_ParametersNR_ForDC_v1550; // optional
    public RRC_CA_ParametersNR_v1560 ca_ParametersNR_ForDC_v1560; // optional
    public RRC_FeatureSetCombinationId featureSetCombinationDC; // optional
}

