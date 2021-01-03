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

public class RRC_DummyE extends AsnSequence {
    public RRC_maxNumberTxPortsPerResource_3 maxNumberTxPortsPerResource; // mandatory
    public AsnInteger maxNumberResources; // mandatory, VALUE(1..64)
    public AsnInteger totalNumberTxPorts; // mandatory, VALUE(2..256)
    public AsnInteger parameterLx; // mandatory, VALUE(2..4)
    public RRC_amplitudeScalingType_1 amplitudeScalingType; // mandatory
    public AsnInteger maxNumberCSI_RS_PerResourceSet; // mandatory, VALUE(1..8)

    public static class RRC_amplitudeScalingType_1 extends AsnEnumerated {
        public static final RRC_amplitudeScalingType_1 WIDEBAND = new RRC_amplitudeScalingType_1(0);
        public static final RRC_amplitudeScalingType_1 WIDEBANDANDSUBBAND = new RRC_amplitudeScalingType_1(1);
    
        private RRC_amplitudeScalingType_1(long value) {
            super(value);
        }
    }

    public static class RRC_maxNumberTxPortsPerResource_3 extends AsnEnumerated {
        public static final RRC_maxNumberTxPortsPerResource_3 P4 = new RRC_maxNumberTxPortsPerResource_3(0);
        public static final RRC_maxNumberTxPortsPerResource_3 P8 = new RRC_maxNumberTxPortsPerResource_3(1);
        public static final RRC_maxNumberTxPortsPerResource_3 P12 = new RRC_maxNumberTxPortsPerResource_3(2);
        public static final RRC_maxNumberTxPortsPerResource_3 P16 = new RRC_maxNumberTxPortsPerResource_3(3);
        public static final RRC_maxNumberTxPortsPerResource_3 P24 = new RRC_maxNumberTxPortsPerResource_3(4);
        public static final RRC_maxNumberTxPortsPerResource_3 P32 = new RRC_maxNumberTxPortsPerResource_3(5);
    
        private RRC_maxNumberTxPortsPerResource_3(long value) {
            super(value);
        }
    }
}

