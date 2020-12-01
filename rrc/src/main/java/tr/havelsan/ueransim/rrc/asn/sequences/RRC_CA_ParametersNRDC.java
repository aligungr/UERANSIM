/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FeatureSetCombinationId;

public class RRC_CA_ParametersNRDC extends RRC_Sequence {

    public RRC_CA_ParametersNR ca_ParametersNR_ForDC;
    public RRC_CA_ParametersNR_v1540 ca_ParametersNR_ForDC_v1540;
    public RRC_CA_ParametersNR_v1550 ca_ParametersNR_ForDC_v1550;
    public RRC_CA_ParametersNR_v1560 ca_ParametersNR_ForDC_v1560;
    public RRC_FeatureSetCombinationId featureSetCombinationDC;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ca-ParametersNR-ForDC","ca-ParametersNR-ForDC-v1540","ca-ParametersNR-ForDC-v1550","ca-ParametersNR-ForDC-v1560","featureSetCombinationDC" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ca_ParametersNR_ForDC","ca_ParametersNR_ForDC_v1540","ca_ParametersNR_ForDC_v1550","ca_ParametersNR_ForDC_v1560","featureSetCombinationDC" };
    }

    @Override
    public String getAsnName() {
        return "CA-ParametersNRDC";
    }

    @Override
    public String getXmlTagName() {
        return "CA-ParametersNRDC";
    }

}
