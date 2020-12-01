/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_UEAssistanceInformation__criticalExtensions;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_UEAssistanceInformation extends RRC_Sequence {

    public RRC_UEAssistanceInformation__criticalExtensions criticalExtensions;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "criticalExtensions" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "criticalExtensions" };
    }

    @Override
    public String getAsnName() {
        return "UEAssistanceInformation";
    }

    @Override
    public String getXmlTagName() {
        return "UEAssistanceInformation";
    }

}
