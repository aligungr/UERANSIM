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

public class RRC_DummyC extends AsnSequence {
    public RRC_maxNumberTxPortsPerResource_2 maxNumberTxPortsPerResource; // mandatory
    public AsnInteger maxNumberResources; // mandatory, VALUE(1..64)
    public AsnInteger totalNumberTxPorts; // mandatory, VALUE(2..256)
    public RRC_supportedCodebookMode_1 supportedCodebookMode; // mandatory
    public RRC_supportedNumberPanels supportedNumberPanels; // mandatory
    public AsnInteger maxNumberCSI_RS_PerResourceSet; // mandatory, VALUE(1..8)

    public static class RRC_supportedNumberPanels extends AsnEnumerated {
        public static final RRC_supportedNumberPanels N2 = new RRC_supportedNumberPanels(0);
        public static final RRC_supportedNumberPanels N4 = new RRC_supportedNumberPanels(1);
    
        private RRC_supportedNumberPanels(long value) {
            super(value);
        }
    }

    public static class RRC_supportedCodebookMode_1 extends AsnEnumerated {
        public static final RRC_supportedCodebookMode_1 MODE1 = new RRC_supportedCodebookMode_1(0);
        public static final RRC_supportedCodebookMode_1 MODE2 = new RRC_supportedCodebookMode_1(1);
        public static final RRC_supportedCodebookMode_1 BOTH = new RRC_supportedCodebookMode_1(2);
    
        private RRC_supportedCodebookMode_1(long value) {
            super(value);
        }
    }

    public static class RRC_maxNumberTxPortsPerResource_2 extends AsnEnumerated {
        public static final RRC_maxNumberTxPortsPerResource_2 P8 = new RRC_maxNumberTxPortsPerResource_2(0);
        public static final RRC_maxNumberTxPortsPerResource_2 P16 = new RRC_maxNumberTxPortsPerResource_2(1);
        public static final RRC_maxNumberTxPortsPerResource_2 P32 = new RRC_maxNumberTxPortsPerResource_2(2);
    
        private RRC_maxNumberTxPortsPerResource_2(long value) {
            super(value);
        }
    }
}

