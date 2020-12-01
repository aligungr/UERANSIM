/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_RedirectedCarrierInfo;
import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_RRCRelease_IEs extends RRC_Sequence {

    public RRC_RedirectedCarrierInfo redirectedCarrierInfo;
    public RRC_CellReselectionPriorities cellReselectionPriorities;
    public RRC_SuspendConfig suspendConfig;
    public RRC_RRCRelease_IEs__deprioritisationReq deprioritisationReq;
    public RRC_OctetString lateNonCriticalExtension;
    public RRC_RRCRelease_v1540_IEs nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "redirectedCarrierInfo","cellReselectionPriorities","suspendConfig","deprioritisationReq","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "redirectedCarrierInfo","cellReselectionPriorities","suspendConfig","deprioritisationReq","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "RRCRelease-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "RRCRelease-IEs";
    }

}
