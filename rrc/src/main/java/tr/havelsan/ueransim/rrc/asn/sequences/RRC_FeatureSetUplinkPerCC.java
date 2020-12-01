/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SupportedBandwidth;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_MIMO_LayersUL;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_ModulationOrder;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;

public class RRC_FeatureSetUplinkPerCC extends RRC_Sequence {

    public RRC_SubcarrierSpacing supportedSubcarrierSpacingUL;
    public RRC_SupportedBandwidth supportedBandwidthUL;
    public RRC_Integer channelBW_90mhz;
    public RRC_FeatureSetUplinkPerCC__mimo_CB_PUSCH mimo_CB_PUSCH;
    public RRC_MIMO_LayersUL maxNumberMIMO_LayersNonCB_PUSCH;
    public RRC_ModulationOrder supportedModulationOrderUL;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "supportedSubcarrierSpacingUL","supportedBandwidthUL","channelBW-90mhz","mimo-CB-PUSCH","maxNumberMIMO-LayersNonCB-PUSCH","supportedModulationOrderUL" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "supportedSubcarrierSpacingUL","supportedBandwidthUL","channelBW_90mhz","mimo_CB_PUSCH","maxNumberMIMO_LayersNonCB_PUSCH","supportedModulationOrderUL" };
    }

    @Override
    public String getAsnName() {
        return "FeatureSetUplinkPerCC";
    }

    @Override
    public String getXmlTagName() {
        return "FeatureSetUplinkPerCC";
    }

}
