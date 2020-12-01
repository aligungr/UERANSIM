/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_BandNR__channelBWs_DL_v1530;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_BandNR__channelBWs_UL_v1530;
import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FreqBandIndicatorNR;

public class RRC_BandNR extends RRC_Sequence {

    public RRC_FreqBandIndicatorNR bandNR;
    public RRC_BitString modifiedMPR_Behaviour;
    public RRC_MIMO_ParametersPerBand mimo_ParametersPerBand;
    public RRC_Integer extendedCP;
    public RRC_Integer multipleTCI;
    public RRC_Integer bwp_WithoutRestriction;
    public RRC_Integer bwp_SameNumerology;
    public RRC_Integer bwp_DiffNumerology;
    public RRC_Integer crossCarrierScheduling_SameSCS;
    public RRC_Integer pdsch_256QAM_FR2;
    public RRC_Integer pusch_256QAM;
    public RRC_Integer ue_PowerClass;
    public RRC_Integer rateMatchingLTE_CRS;
    public RRC_BandNR__channelBWs_DL_v1530 channelBWs_DL_v1530;
    public RRC_BandNR__channelBWs_UL_v1530 channelBWs_UL_v1530;
    public RRC_BandNR__ext1 ext1;
    public RRC_BandNR__ext2 ext2;
    public RRC_BandNR__ext3 ext3;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "bandNR","modifiedMPR-Behaviour","mimo-ParametersPerBand","extendedCP","multipleTCI","bwp-WithoutRestriction","bwp-SameNumerology","bwp-DiffNumerology","crossCarrierScheduling-SameSCS","pdsch-256QAM-FR2","pusch-256QAM","ue-PowerClass","rateMatchingLTE-CRS","channelBWs-DL-v1530","channelBWs-UL-v1530","ext1","ext2","ext3" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "bandNR","modifiedMPR_Behaviour","mimo_ParametersPerBand","extendedCP","multipleTCI","bwp_WithoutRestriction","bwp_SameNumerology","bwp_DiffNumerology","crossCarrierScheduling_SameSCS","pdsch_256QAM_FR2","pusch_256QAM","ue_PowerClass","rateMatchingLTE_CRS","channelBWs_DL_v1530","channelBWs_UL_v1530","ext1","ext2","ext3" };
    }

    @Override
    public String getAsnName() {
        return "BandNR";
    }

    @Override
    public String getXmlTagName() {
        return "BandNR";
    }

}
