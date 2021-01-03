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

public class RRC_CA_ParametersNR_v1540 extends AsnSequence {
    public AsnInteger simultaneousSRS_AssocCSI_RS_AllCC; // optional, VALUE(5..32)
    public RRC_csi_RS_IM_ReceptionForFeedbackPerBandComb csi_RS_IM_ReceptionForFeedbackPerBandComb; // optional
    public AsnInteger simultaneousCSI_ReportsAllCC; // optional, VALUE(5..32)
    public RRC_dualPA_Architecture_2 dualPA_Architecture; // optional

    public static class RRC_dualPA_Architecture_2 extends AsnEnumerated {
        public static final RRC_dualPA_Architecture_2 SUPPORTED = new RRC_dualPA_Architecture_2(0);
    
        private RRC_dualPA_Architecture_2(long value) {
            super(value);
        }
    }

    public static class RRC_csi_RS_IM_ReceptionForFeedbackPerBandComb extends AsnSequence {
        public AsnInteger maxNumberSimultaneousNZP_CSI_RS_ActBWP_AllCC; // optional, VALUE(1..64)
        public AsnInteger totalNumberPortsSimultaneousNZP_CSI_RS_ActBWP_AllCC; // optional, VALUE(2..256)
    }
}

