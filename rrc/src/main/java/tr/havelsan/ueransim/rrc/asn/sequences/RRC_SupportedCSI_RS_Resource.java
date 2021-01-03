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

public class RRC_SupportedCSI_RS_Resource extends AsnSequence {
    public RRC_maxNumberTxPortsPerResource_5 maxNumberTxPortsPerResource; // mandatory
    public AsnInteger maxNumberResourcesPerBand; // mandatory, VALUE(1..64)
    public AsnInteger totalNumberTxPortsPerBand; // mandatory, VALUE(2..256)

    public static class RRC_maxNumberTxPortsPerResource_5 extends AsnEnumerated {
        public static final RRC_maxNumberTxPortsPerResource_5 P2 = new RRC_maxNumberTxPortsPerResource_5(0);
        public static final RRC_maxNumberTxPortsPerResource_5 P4 = new RRC_maxNumberTxPortsPerResource_5(1);
        public static final RRC_maxNumberTxPortsPerResource_5 P8 = new RRC_maxNumberTxPortsPerResource_5(2);
        public static final RRC_maxNumberTxPortsPerResource_5 P12 = new RRC_maxNumberTxPortsPerResource_5(3);
        public static final RRC_maxNumberTxPortsPerResource_5 P16 = new RRC_maxNumberTxPortsPerResource_5(4);
        public static final RRC_maxNumberTxPortsPerResource_5 P24 = new RRC_maxNumberTxPortsPerResource_5(5);
        public static final RRC_maxNumberTxPortsPerResource_5 P32 = new RRC_maxNumberTxPortsPerResource_5(6);
    
        private RRC_maxNumberTxPortsPerResource_5(long value) {
            super(value);
        }
    }
}

