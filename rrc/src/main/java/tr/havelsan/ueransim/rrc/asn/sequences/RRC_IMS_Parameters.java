package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_IMS_Parameters extends AsnSequence {
    public RRC_IMS_ParametersCommon ims_ParametersCommon; // optional
    public RRC_IMS_ParametersFRX_Diff ims_ParametersFRX_Diff; // optional
}

