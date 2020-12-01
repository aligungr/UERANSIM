/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_UEAssistanceInformation_v1540_IEs extends RRC_Sequence {

    public RRC_OverheatingAssistance overheatingAssistance;
    public RRC_UEAssistanceInformation_v1540_IEs__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "overheatingAssistance","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "overheatingAssistance","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "UEAssistanceInformation-v1540-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "UEAssistanceInformation-v1540-IEs";
    }

}
