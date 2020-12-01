/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_PUCCH_ConfigCommon;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_PUSCH_ConfigCommon;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_RACH_ConfigCommon;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_BWP_UplinkCommon extends RRC_Sequence {

    public RRC_BWP genericParameters;
    public RRC_SetupRelease_RACH_ConfigCommon rach_ConfigCommon;
    public RRC_SetupRelease_PUSCH_ConfigCommon pusch_ConfigCommon;
    public RRC_SetupRelease_PUCCH_ConfigCommon pucch_ConfigCommon;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "genericParameters","rach-ConfigCommon","pusch-ConfigCommon","pucch-ConfigCommon" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "genericParameters","rach_ConfigCommon","pusch_ConfigCommon","pucch_ConfigCommon" };
    }

    @Override
    public String getAsnName() {
        return "BWP-UplinkCommon";
    }

    @Override
    public String getXmlTagName() {
        return "BWP-UplinkCommon";
    }

}
