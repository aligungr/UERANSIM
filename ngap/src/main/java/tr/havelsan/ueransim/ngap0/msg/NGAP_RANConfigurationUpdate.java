/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.msg;

import tr.havelsan.ueransim.ngap0.NgapMessageType;
import tr.havelsan.ueransim.ngap0.NgapProtocolIeType;
import tr.havelsan.ueransim.ngap0.core.NGAP_BaseMessage;
import tr.havelsan.ueransim.ngap0.core.NGAP_Value;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_GlobalRANNodeID;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_PagingDRX;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.NGAP_RANNodeName;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_NGRAN_TNLAssociationToRemoveList;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_SupportedTAList;

public class NGAP_RANConfigurationUpdate extends NGAP_BaseMessage {

    public NGAP_RANConfigurationUpdate() {

    }

    @Override
    public NgapMessageType getMessageType() {
        return NgapMessageType.RANConfigurationUpdate;
    }

    @Override
    public NgapProtocolIeType getProtocolIeType() {
        return NgapProtocolIeType.forMessage("RANConfigurationUpdate");
    }

    @Override
    public int getCriticality() {
        return 0;
    }

    @Override
    public int getProcedureCode() {
        return 35;
    }

    @Override
    public int getPduType() {
        return 0;
    }

    @Override
    public int[] getIeId() {
        return new int[] { 82, 102, 21, 27, 167 };
    }

    @Override
    public int[] getIeCriticality() {
        return new int[] { 1, 0, 1, 1, 0 };
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[] { NGAP_RANNodeName.class, NGAP_SupportedTAList.class, NGAP_PagingDRX.class, NGAP_GlobalRANNodeID.class, NGAP_NGRAN_TNLAssociationToRemoveList.class };
    }

    @Override
    public int[] getIePresence() {
        return new int[] { 0, 0, 0, 0, 0 };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[] { "protocolIEs" };
    }

    @Override
    public String[] getMemberNames() {
        return new String[] { "protocolIEs" };
    }

    @Override
    public String getAsnName() {
        return "RANConfigurationUpdate";
    }

    @Override
    public String getXmlTagName() {
        return "RANConfigurationUpdate";
    }

}
