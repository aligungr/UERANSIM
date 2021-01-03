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

public class RRC_DummyB extends AsnSequence {
    public RRC_maxNumberTxPortsPerResource_4 maxNumberTxPortsPerResource; // mandatory
    public AsnInteger maxNumberResources; // mandatory, VALUE(1..64)
    public AsnInteger totalNumberTxPorts; // mandatory, VALUE(2..256)
    public RRC_supportedCodebookMode_2 supportedCodebookMode; // mandatory
    public AsnInteger maxNumberCSI_RS_PerResourceSet; // mandatory, VALUE(1..8)

    public static class RRC_maxNumberTxPortsPerResource_4 extends AsnEnumerated {
        public static final RRC_maxNumberTxPortsPerResource_4 P2 = new RRC_maxNumberTxPortsPerResource_4(0);
        public static final RRC_maxNumberTxPortsPerResource_4 P4 = new RRC_maxNumberTxPortsPerResource_4(1);
        public static final RRC_maxNumberTxPortsPerResource_4 P8 = new RRC_maxNumberTxPortsPerResource_4(2);
        public static final RRC_maxNumberTxPortsPerResource_4 P12 = new RRC_maxNumberTxPortsPerResource_4(3);
        public static final RRC_maxNumberTxPortsPerResource_4 P16 = new RRC_maxNumberTxPortsPerResource_4(4);
        public static final RRC_maxNumberTxPortsPerResource_4 P24 = new RRC_maxNumberTxPortsPerResource_4(5);
        public static final RRC_maxNumberTxPortsPerResource_4 P32 = new RRC_maxNumberTxPortsPerResource_4(6);
    
        private RRC_maxNumberTxPortsPerResource_4(long value) {
            super(value);
        }
    }

    public static class RRC_supportedCodebookMode_2 extends AsnEnumerated {
        public static final RRC_supportedCodebookMode_2 MODE1 = new RRC_supportedCodebookMode_2(0);
        public static final RRC_supportedCodebookMode_2 MODE1ANDMODE2 = new RRC_supportedCodebookMode_2(1);
    
        private RRC_supportedCodebookMode_2(long value) {
            super(value);
        }
    }
}

