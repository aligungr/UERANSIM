/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_PDCCH_ConfigCommon;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_PDSCH_ConfigCommon;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_BWP_DownlinkCommon extends RRC_Sequence {

    public RRC_BWP genericParameters;
    public RRC_SetupRelease_PDCCH_ConfigCommon pdcch_ConfigCommon;
    public RRC_SetupRelease_PDSCH_ConfigCommon pdsch_ConfigCommon;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "genericParameters","pdcch-ConfigCommon","pdsch-ConfigCommon" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "genericParameters","pdcch_ConfigCommon","pdsch_ConfigCommon" };
    }

    @Override
    public String getAsnName() {
        return "BWP-DownlinkCommon";
    }

    @Override
    public String getXmlTagName() {
        return "BWP-DownlinkCommon";
    }

}
