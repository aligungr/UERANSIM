/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBitString;
import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_BandParameters;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FeatureSetCombinationId;

public class RRC_BandCombination extends AsnSequence {
    public RRC_bandList bandList; // mandatory, SIZE(1..32)
    public RRC_FeatureSetCombinationId featureSetCombination; // mandatory
    public RRC_CA_ParametersEUTRA ca_ParametersEUTRA; // optional
    public RRC_CA_ParametersNR ca_ParametersNR; // optional
    public RRC_MRDC_Parameters mrdc_Parameters; // optional
    public AsnBitString supportedBandwidthCombinationSet; // optional, SIZE(1..32)
    public RRC_powerClass_v1530 powerClass_v1530; // optional

    public static class RRC_powerClass_v1530 extends AsnEnumerated {
        public static final RRC_powerClass_v1530 PC2 = new RRC_powerClass_v1530(0);
    
        private RRC_powerClass_v1530(long value) {
            super(value);
        }
    }

    // SIZE(1..32)
    public static class RRC_bandList extends AsnSequenceOf<RRC_BandParameters> {
    }
}

