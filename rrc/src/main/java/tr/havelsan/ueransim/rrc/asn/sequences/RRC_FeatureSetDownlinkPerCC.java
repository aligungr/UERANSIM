/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SupportedBandwidth;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_MIMO_LayersDL;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_ModulationOrder;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;

public class RRC_FeatureSetDownlinkPerCC extends RRC_Sequence {

    public RRC_SubcarrierSpacing supportedSubcarrierSpacingDL;
    public RRC_SupportedBandwidth supportedBandwidthDL;
    public RRC_Integer channelBW_90mhz;
    public RRC_MIMO_LayersDL maxNumberMIMO_LayersPDSCH;
    public RRC_ModulationOrder supportedModulationOrderDL;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "supportedSubcarrierSpacingDL","supportedBandwidthDL","channelBW-90mhz","maxNumberMIMO-LayersPDSCH","supportedModulationOrderDL" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "supportedSubcarrierSpacingDL","supportedBandwidthDL","channelBW_90mhz","maxNumberMIMO_LayersPDSCH","supportedModulationOrderDL" };
    }

    @Override
    public String getAsnName() {
        return "FeatureSetDownlinkPerCC";
    }

    @Override
    public String getXmlTagName() {
        return "FeatureSetDownlinkPerCC";
    }

}
