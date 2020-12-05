package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_I_RNTI_Value;
import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_NG_5G_S_TMSI;

public class RRC_PagingUE_Identity extends AsnChoice {
    public RRC_NG_5G_S_TMSI ng_5G_S_TMSI;
    public RRC_I_RNTI_Value fullI_RNTI;
}

