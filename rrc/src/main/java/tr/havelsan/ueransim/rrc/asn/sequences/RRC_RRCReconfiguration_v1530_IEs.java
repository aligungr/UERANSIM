/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_RRCReconfiguration_v1530_IEs__dedicatedNAS_MessageList;

public class RRC_RRCReconfiguration_v1530_IEs extends RRC_Sequence {

    public RRC_OctetString masterCellGroup;
    public RRC_Integer fullConfig;
    public RRC_RRCReconfiguration_v1530_IEs__dedicatedNAS_MessageList dedicatedNAS_MessageList;
    public RRC_MasterKeyUpdate masterKeyUpdate;
    public RRC_OctetString dedicatedSIB1_Delivery;
    public RRC_OctetString dedicatedSystemInformationDelivery;
    public RRC_OtherConfig otherConfig;
    public RRC_RRCReconfiguration_v1540_IEs nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "masterCellGroup","fullConfig","dedicatedNAS-MessageList","masterKeyUpdate","dedicatedSIB1-Delivery","dedicatedSystemInformationDelivery","otherConfig","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "masterCellGroup","fullConfig","dedicatedNAS_MessageList","masterKeyUpdate","dedicatedSIB1_Delivery","dedicatedSystemInformationDelivery","otherConfig","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "RRCReconfiguration-v1530-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "RRCReconfiguration-v1530-IEs";
    }

}
