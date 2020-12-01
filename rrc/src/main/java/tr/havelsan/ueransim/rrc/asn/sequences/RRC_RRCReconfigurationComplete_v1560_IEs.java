/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_RRCReconfigurationComplete_v1560_IEs__scg_Response;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_RRCReconfigurationComplete_v1560_IEs extends RRC_Sequence {

    public RRC_RRCReconfigurationComplete_v1560_IEs__scg_Response scg_Response;
    public RRC_RRCReconfigurationComplete_v1560_IEs__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "scg-Response","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "scg_Response","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "RRCReconfigurationComplete-v1560-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "RRCReconfigurationComplete-v1560-IEs";
    }

}
