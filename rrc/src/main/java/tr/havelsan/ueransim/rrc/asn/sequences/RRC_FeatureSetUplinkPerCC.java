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
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SupportedBandwidth;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_MIMO_LayersUL;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_ModulationOrder;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;

public class RRC_FeatureSetUplinkPerCC extends AsnSequence {
    public RRC_SubcarrierSpacing supportedSubcarrierSpacingUL; // mandatory
    public RRC_SupportedBandwidth supportedBandwidthUL; // mandatory
    public RRC_channelBW_90mhz_1 channelBW_90mhz; // optional
    public RRC_mimo_CB_PUSCH mimo_CB_PUSCH; // optional
    public RRC_MIMO_LayersUL maxNumberMIMO_LayersNonCB_PUSCH; // optional
    public RRC_ModulationOrder supportedModulationOrderUL; // optional

    public static class RRC_channelBW_90mhz_1 extends AsnEnumerated {
        public static final RRC_channelBW_90mhz_1 SUPPORTED = new RRC_channelBW_90mhz_1(0);
    
        private RRC_channelBW_90mhz_1(long value) {
            super(value);
        }
    }

    public static class RRC_mimo_CB_PUSCH extends AsnSequence {
        public RRC_MIMO_LayersUL maxNumberMIMO_LayersCB_PUSCH; // optional
        public AsnInteger maxNumberSRS_ResourcePerSet; // mandatory, VALUE(1..2)
    }
}

