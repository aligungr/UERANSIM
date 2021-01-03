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
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_EUTRA_MBSFN_SubframeConfigList;

public class RRC_RateMatchPatternLTE_CRS extends AsnSequence {
    public AsnInteger carrierFreqDL; // mandatory, VALUE(0..16383)
    public RRC_carrierBandwidthDL carrierBandwidthDL; // mandatory
    public RRC_EUTRA_MBSFN_SubframeConfigList mbsfn_SubframeConfigList; // optional
    public RRC_nrofCRS_Ports nrofCRS_Ports; // mandatory
    public RRC_v_Shift v_Shift; // mandatory

    public static class RRC_carrierBandwidthDL extends AsnEnumerated {
        public static final RRC_carrierBandwidthDL N6 = new RRC_carrierBandwidthDL(0);
        public static final RRC_carrierBandwidthDL N15 = new RRC_carrierBandwidthDL(1);
        public static final RRC_carrierBandwidthDL N25 = new RRC_carrierBandwidthDL(2);
        public static final RRC_carrierBandwidthDL N50 = new RRC_carrierBandwidthDL(3);
        public static final RRC_carrierBandwidthDL N75 = new RRC_carrierBandwidthDL(4);
        public static final RRC_carrierBandwidthDL N100 = new RRC_carrierBandwidthDL(5);
        public static final RRC_carrierBandwidthDL SPARE2 = new RRC_carrierBandwidthDL(6);
        public static final RRC_carrierBandwidthDL SPARE1 = new RRC_carrierBandwidthDL(7);
    
        private RRC_carrierBandwidthDL(long value) {
            super(value);
        }
    }

    public static class RRC_nrofCRS_Ports extends AsnEnumerated {
        public static final RRC_nrofCRS_Ports N1 = new RRC_nrofCRS_Ports(0);
        public static final RRC_nrofCRS_Ports N2 = new RRC_nrofCRS_Ports(1);
        public static final RRC_nrofCRS_Ports N4 = new RRC_nrofCRS_Ports(2);
    
        private RRC_nrofCRS_Ports(long value) {
            super(value);
        }
    }

    public static class RRC_v_Shift extends AsnEnumerated {
        public static final RRC_v_Shift N0 = new RRC_v_Shift(0);
        public static final RRC_v_Shift N1 = new RRC_v_Shift(1);
        public static final RRC_v_Shift N2 = new RRC_v_Shift(2);
        public static final RRC_v_Shift N3 = new RRC_v_Shift(3);
        public static final RRC_v_Shift N4 = new RRC_v_Shift(4);
        public static final RRC_v_Shift N5 = new RRC_v_Shift(5);
    
        private RRC_v_Shift(long value) {
            super(value);
        }
    }
}

