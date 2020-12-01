/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_OverheatingAssistance extends RRC_Sequence {

    public RRC_OverheatingAssistance__reducedMaxCCs reducedMaxCCs;
    public RRC_OverheatingAssistance__reducedMaxBW_FR1 reducedMaxBW_FR1;
    public RRC_OverheatingAssistance__reducedMaxBW_FR2 reducedMaxBW_FR2;
    public RRC_OverheatingAssistance__reducedMaxMIMO_LayersFR1 reducedMaxMIMO_LayersFR1;
    public RRC_OverheatingAssistance__reducedMaxMIMO_LayersFR2 reducedMaxMIMO_LayersFR2;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "reducedMaxCCs","reducedMaxBW-FR1","reducedMaxBW-FR2","reducedMaxMIMO-LayersFR1","reducedMaxMIMO-LayersFR2" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "reducedMaxCCs","reducedMaxBW_FR1","reducedMaxBW_FR2","reducedMaxMIMO_LayersFR1","reducedMaxMIMO_LayersFR2" };
    }

    @Override
    public String getAsnName() {
        return "OverheatingAssistance";
    }

    @Override
    public String getXmlTagName() {
        return "OverheatingAssistance";
    }

}
