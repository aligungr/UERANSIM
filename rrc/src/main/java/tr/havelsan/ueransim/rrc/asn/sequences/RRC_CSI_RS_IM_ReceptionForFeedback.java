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

public class RRC_CSI_RS_IM_ReceptionForFeedback extends AsnSequence {
    public AsnInteger maxConfigNumberNZP_CSI_RS_PerCC; // mandatory, VALUE(1..64)
    public AsnInteger maxConfigNumberPortsAcrossNZP_CSI_RS_PerCC; // mandatory, VALUE(2..256)
    public RRC_maxConfigNumberCSI_IM_PerCC maxConfigNumberCSI_IM_PerCC; // mandatory
    public AsnInteger maxNumberSimultaneousNZP_CSI_RS_PerCC; // mandatory, VALUE(1..64)
    public AsnInteger totalNumberPortsSimultaneousNZP_CSI_RS_PerCC; // mandatory, VALUE(2..256)

    public static class RRC_maxConfigNumberCSI_IM_PerCC extends AsnEnumerated {
        public static final RRC_maxConfigNumberCSI_IM_PerCC N1 = new RRC_maxConfigNumberCSI_IM_PerCC(0);
        public static final RRC_maxConfigNumberCSI_IM_PerCC N2 = new RRC_maxConfigNumberCSI_IM_PerCC(1);
        public static final RRC_maxConfigNumberCSI_IM_PerCC N4 = new RRC_maxConfigNumberCSI_IM_PerCC(2);
        public static final RRC_maxConfigNumberCSI_IM_PerCC N8 = new RRC_maxConfigNumberCSI_IM_PerCC(3);
        public static final RRC_maxConfigNumberCSI_IM_PerCC N16 = new RRC_maxConfigNumberCSI_IM_PerCC(4);
        public static final RRC_maxConfigNumberCSI_IM_PerCC N32 = new RRC_maxConfigNumberCSI_IM_PerCC(5);
    
        private RRC_maxConfigNumberCSI_IM_PerCC(long value) {
            super(value);
        }
    }
}

