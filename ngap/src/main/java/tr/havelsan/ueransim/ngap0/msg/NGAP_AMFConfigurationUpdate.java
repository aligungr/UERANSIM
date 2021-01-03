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
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_RelativeAMFCapacity;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.NGAP_AMFName;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;

public class NGAP_AMFConfigurationUpdate extends NGAP_BaseMessage {

    public NGAP_AMFConfigurationUpdate() {

    }

    @Override
    public NgapMessageType getMessageType() {
        return NgapMessageType.AMFConfigurationUpdate;
    }

    @Override
    public NgapProtocolIeType getProtocolIeType() {
        return NgapProtocolIeType.forMessage("AMFConfigurationUpdate");
    }

    @Override
    public int getCriticality() {
        return 0;
    }

    @Override
    public int getProcedureCode() {
        return 0;
    }

    @Override
    public int getPduType() {
        return 0;
    }

    @Override
    public int[] getIeId() {
        return new int[] { 1, 96, 86, 80, 6, 7, 8 };
    }

    @Override
    public int[] getIeCriticality() {
        return new int[] { 0, 0, 1, 0, 1, 1, 1 };
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[] { NGAP_AMFName.class, NGAP_ServedGUAMIList.class, NGAP_RelativeAMFCapacity.class, NGAP_PLMNSupportList.class, NGAP_AMF_TNLAssociationToAddList.class, NGAP_AMF_TNLAssociationToRemoveList.class, NGAP_AMF_TNLAssociationToUpdateList.class };
    }

    @Override
    public int[] getIePresence() {
        return new int[] { 0, 0, 0, 0, 0, 0, 0 };
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
        return "AMFConfigurationUpdate";
    }

    @Override
    public String getXmlTagName() {
        return "AMFConfigurationUpdate";
    }

}
