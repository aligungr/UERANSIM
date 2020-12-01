/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PDU_SessionID;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_SDAP_Config__mappedQoS_FlowsToAdd;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_SDAP_Config__mappedQoS_FlowsToRelease;

public class RRC_SDAP_Config extends RRC_Sequence {

    public RRC_PDU_SessionID pdu_Session;
    public RRC_Integer sdap_HeaderDL;
    public RRC_Integer sdap_HeaderUL;
    public RRC_Boolean defaultDRB;
    public RRC_SDAP_Config__mappedQoS_FlowsToAdd mappedQoS_FlowsToAdd;
    public RRC_SDAP_Config__mappedQoS_FlowsToRelease mappedQoS_FlowsToRelease;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "pdu-Session","sdap-HeaderDL","sdap-HeaderUL","defaultDRB","mappedQoS-FlowsToAdd","mappedQoS-FlowsToRelease" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "pdu_Session","sdap_HeaderDL","sdap_HeaderUL","defaultDRB","mappedQoS_FlowsToAdd","mappedQoS_FlowsToRelease" };
    }

    @Override
    public String getAsnName() {
        return "SDAP-Config";
    }

    @Override
    public String getXmlTagName() {
        return "SDAP-Config";
    }

}
