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
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_OverloadResponse;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_TrafficLoadReductionIndication;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_OverloadStartNSSAIList;

public class NGAP_OverloadStart extends NGAP_BaseMessage {

    public NGAP_OverloadStart() {

    }

    @Override
    public NgapMessageType getMessageType() {
        return NgapMessageType.OverloadStart;
    }

    @Override
    public NgapProtocolIeType getProtocolIeType() {
        return NgapProtocolIeType.forMessage("OverloadStart");
    }

    @Override
    public int getCriticality() {
        return 1;
    }

    @Override
    public int getProcedureCode() {
        return 22;
    }

    @Override
    public int getPduType() {
        return 0;
    }

    @Override
    public int[] getIeId() {
        return new int[] { 2, 9, 49 };
    }

    @Override
    public int[] getIeCriticality() {
        return new int[] { 0, 1, 1 };
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[] { NGAP_OverloadResponse.class, NGAP_TrafficLoadReductionIndication.class, NGAP_OverloadStartNSSAIList.class };
    }

    @Override
    public int[] getIePresence() {
        return new int[] { 0, 0, 0 };
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
        return "OverloadStart";
    }

    @Override
    public String getXmlTagName() {
        return "OverloadStart";
    }

}
