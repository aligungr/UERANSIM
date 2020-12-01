/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FeatureSetCombinationId;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_BandCombination__bandList;

public class RRC_BandCombination extends RRC_Sequence {

    public RRC_BandCombination__bandList bandList;
    public RRC_FeatureSetCombinationId featureSetCombination;
    public RRC_CA_ParametersEUTRA ca_ParametersEUTRA;
    public RRC_CA_ParametersNR ca_ParametersNR;
    public RRC_MRDC_Parameters mrdc_Parameters;
    public RRC_BitString supportedBandwidthCombinationSet;
    public RRC_Integer powerClass_v1530;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "bandList","featureSetCombination","ca-ParametersEUTRA","ca-ParametersNR","mrdc-Parameters","supportedBandwidthCombinationSet","powerClass-v1530" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "bandList","featureSetCombination","ca_ParametersEUTRA","ca_ParametersNR","mrdc_Parameters","supportedBandwidthCombinationSet","powerClass_v1530" };
    }

    @Override
    public String getAsnName() {
        return "BandCombination";
    }

    @Override
    public String getXmlTagName() {
        return "BandCombination";
    }

}
