/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SupportedBandwidth;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_MIMO_LayersDL;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_ModulationOrder;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;

public class RRC_FeatureSetDownlinkPerCC extends AsnSequence {
    public RRC_SubcarrierSpacing supportedSubcarrierSpacingDL; // mandatory
    public RRC_SupportedBandwidth supportedBandwidthDL; // mandatory
    public RRC_channelBW_90mhz_2 channelBW_90mhz; // optional
    public RRC_MIMO_LayersDL maxNumberMIMO_LayersPDSCH; // optional
    public RRC_ModulationOrder supportedModulationOrderDL; // optional

    public static class RRC_channelBW_90mhz_2 extends AsnEnumerated {
        public static final RRC_channelBW_90mhz_2 SUPPORTED = new RRC_channelBW_90mhz_2(0);
    
        private RRC_channelBW_90mhz_2(long value) {
            super(value);
        }
    }
}

