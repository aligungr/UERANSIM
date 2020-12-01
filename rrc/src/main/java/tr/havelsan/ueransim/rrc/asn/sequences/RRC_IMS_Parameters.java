/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_IMS_Parameters extends RRC_Sequence {

    public RRC_IMS_ParametersCommon ims_ParametersCommon;
    public RRC_IMS_ParametersFRX_Diff ims_ParametersFRX_Diff;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ims-ParametersCommon","ims-ParametersFRX-Diff" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ims_ParametersCommon","ims_ParametersFRX_Diff" };
    }

    @Override
    public String getAsnName() {
        return "IMS-Parameters";
    }

    @Override
    public String getXmlTagName() {
        return "IMS-Parameters";
    }

}
