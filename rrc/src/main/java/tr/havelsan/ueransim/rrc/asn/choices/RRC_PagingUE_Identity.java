/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_I_RNTI_Value;
import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_NG_5G_S_TMSI;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;

public class RRC_PagingUE_Identity extends RRC_Choice {

    public RRC_NG_5G_S_TMSI ng_5G_S_TMSI;
    public RRC_I_RNTI_Value fullI_RNTI;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ng-5G-S-TMSI","fullI-RNTI" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ng_5G_S_TMSI","fullI_RNTI" };
    }

    @Override
    public String getAsnName() {
        return "PagingUE-Identity";
    }

    @Override
    public String getXmlTagName() {
        return "PagingUE-Identity";
    }

}
