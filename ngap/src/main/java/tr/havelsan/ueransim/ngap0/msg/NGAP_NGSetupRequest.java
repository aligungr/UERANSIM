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
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_UERetentionInformation;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.NGAP_RANNodeName;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_SupportedTAList;

public class NGAP_NGSetupRequest extends NGAP_BaseMessage {

    public NGAP_NGSetupRequest() {

    }

    @Override
    public NgapMessageType getMessageType() {
        return NgapMessageType.NGSetupRequest;
    }

    @Override
    public NgapProtocolIeType getProtocolIeType() {
        return NgapProtocolIeType.forMessage("NGSetupRequest");
    }

    @Override
    public int getCriticality() {
        return 0;
    }

    @Override
    public int getProcedureCode() {
        return 21;
    }

    @Override
    public int getPduType() {
        return 0;
    }

    @Override
    public int[] getIeId() {
        return new int[] { 27, 82, 102, 21, 147 };
    }

    @Override
    public int[] getIeCriticality() {
        return new int[] { 0, 1, 0, 1, 1 };
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[] { NGAP_GlobalRANNodeID.class, NGAP_RANNodeName.class, NGAP_SupportedTAList.class, NGAP_PagingDRX.class, NGAP_UERetentionInformation.class };
    }

    @Override
    public int[] getIePresence() {
        return new int[] { 2, 0, 2, 2, 0 };
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
        return "NGSetupRequest";
    }

    @Override
    public String getXmlTagName() {
        return "NGSetupRequest";
    }

}
