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
import tr.havelsan.ueransim.rrc.asn.choices.RRC_CSI_ResourcePeriodicityAndOffset;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_NZP_CSI_RS_ResourceId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ScramblingId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_TCI_StateId;

public class RRC_NZP_CSI_RS_Resource extends AsnSequence {
    public RRC_NZP_CSI_RS_ResourceId nzp_CSI_RS_ResourceId; // mandatory
    public RRC_CSI_RS_ResourceMapping resourceMapping; // mandatory
    public AsnInteger powerControlOffset; // mandatory, VALUE(-8..15)
    public RRC_powerControlOffsetSS powerControlOffsetSS; // optional
    public RRC_ScramblingId scramblingID; // mandatory
    public RRC_CSI_ResourcePeriodicityAndOffset periodicityAndOffset; // optional
    public RRC_TCI_StateId qcl_InfoPeriodicCSI_RS; // optional

    public static class RRC_powerControlOffsetSS extends AsnEnumerated {
        public static final RRC_powerControlOffsetSS DB_3 = new RRC_powerControlOffsetSS(0);
        public static final RRC_powerControlOffsetSS DB0 = new RRC_powerControlOffsetSS(1);
        public static final RRC_powerControlOffsetSS DB3 = new RRC_powerControlOffsetSS(2);
        public static final RRC_powerControlOffsetSS DB6 = new RRC_powerControlOffsetSS(3);
    
        private RRC_powerControlOffsetSS(long value) {
            super(value);
        }
    }
}

