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
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_MessageIdentifier;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_SerialNumber;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_WarningAreaList;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_CancelAllWarningMessages;

public class NGAP_PWSCancelRequest extends NGAP_BaseMessage {

    public NGAP_PWSCancelRequest() {

    }

    @Override
    public NgapMessageType getMessageType() {
        return NgapMessageType.PWSCancelRequest;
    }

    @Override
    public NgapProtocolIeType getProtocolIeType() {
        return NgapProtocolIeType.forMessage("PWSCancelRequest");
    }

    @Override
    public int getCriticality() {
        return 0;
    }

    @Override
    public int getProcedureCode() {
        return 32;
    }

    @Override
    public int getPduType() {
        return 0;
    }

    @Override
    public int[] getIeId() {
        return new int[] { 35, 95, 122, 14 };
    }

    @Override
    public int[] getIeCriticality() {
        return new int[] { 0, 0, 1, 0 };
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[] { NGAP_MessageIdentifier.class, NGAP_SerialNumber.class, NGAP_WarningAreaList.class, NGAP_CancelAllWarningMessages.class };
    }

    @Override
    public int[] getIePresence() {
        return new int[] { 2, 2, 0, 0 };
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
        return "PWSCancelRequest";
    }

    @Override
    public String getXmlTagName() {
        return "PWSCancelRequest";
    }

}
