/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_DummyD extends AsnSequence {
    public RRC_maxNumberTxPortsPerResource_1 maxNumberTxPortsPerResource; // mandatory
    public AsnInteger maxNumberResources; // mandatory, VALUE(1..64)
    public AsnInteger totalNumberTxPorts; // mandatory, VALUE(2..256)
    public AsnInteger parameterLx; // mandatory, VALUE(2..4)
    public RRC_amplitudeScalingType_4 amplitudeScalingType; // mandatory
    public RRC_amplitudeSubsetRestriction_1 amplitudeSubsetRestriction; // optional
    public AsnInteger maxNumberCSI_RS_PerResourceSet; // mandatory, VALUE(1..8)

    public static class RRC_amplitudeSubsetRestriction_1 extends AsnEnumerated {
        public static final RRC_amplitudeSubsetRestriction_1 SUPPORTED = new RRC_amplitudeSubsetRestriction_1(0);
    
        private RRC_amplitudeSubsetRestriction_1(long value) {
            super(value);
        }
    }

    public static class RRC_maxNumberTxPortsPerResource_1 extends AsnEnumerated {
        public static final RRC_maxNumberTxPortsPerResource_1 P4 = new RRC_maxNumberTxPortsPerResource_1(0);
        public static final RRC_maxNumberTxPortsPerResource_1 P8 = new RRC_maxNumberTxPortsPerResource_1(1);
        public static final RRC_maxNumberTxPortsPerResource_1 P12 = new RRC_maxNumberTxPortsPerResource_1(2);
        public static final RRC_maxNumberTxPortsPerResource_1 P16 = new RRC_maxNumberTxPortsPerResource_1(3);
        public static final RRC_maxNumberTxPortsPerResource_1 P24 = new RRC_maxNumberTxPortsPerResource_1(4);
        public static final RRC_maxNumberTxPortsPerResource_1 P32 = new RRC_maxNumberTxPortsPerResource_1(5);
    
        private RRC_maxNumberTxPortsPerResource_1(long value) {
            super(value);
        }
    }

    public static class RRC_amplitudeScalingType_4 extends AsnEnumerated {
        public static final RRC_amplitudeScalingType_4 WIDEBAND = new RRC_amplitudeScalingType_4(0);
        public static final RRC_amplitudeScalingType_4 WIDEBANDANDSUBBAND = new RRC_amplitudeScalingType_4(1);
    
        private RRC_amplitudeScalingType_4(long value) {
            super(value);
        }
    }
}

