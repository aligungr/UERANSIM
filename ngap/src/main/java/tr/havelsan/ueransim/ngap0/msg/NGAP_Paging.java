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
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_UEPagingIdentity;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_PagingDRX;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_PagingOrigin;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_PagingPriority;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_TAIListForPaging;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_AssistanceDataForPaging;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_UERadioCapabilityForPaging;

public class NGAP_Paging extends NGAP_BaseMessage {

    public NGAP_Paging() {

    }

    @Override
    public NgapMessageType getMessageType() {
        return NgapMessageType.Paging;
    }

    @Override
    public NgapProtocolIeType getProtocolIeType() {
        return NgapProtocolIeType.forMessage("Paging");
    }

    @Override
    public int getCriticality() {
        return 1;
    }

    @Override
    public int getProcedureCode() {
        return 24;
    }

    @Override
    public int getPduType() {
        return 0;
    }

    @Override
    public int[] getIeId() {
        return new int[] { 115, 50, 103, 52, 118, 51, 11 };
    }

    @Override
    public int[] getIeCriticality() {
        return new int[] { 1, 1, 1, 1, 1, 1, 1 };
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[] { NGAP_UEPagingIdentity.class, NGAP_PagingDRX.class, NGAP_TAIListForPaging.class, NGAP_PagingPriority.class, NGAP_UERadioCapabilityForPaging.class, NGAP_PagingOrigin.class, NGAP_AssistanceDataForPaging.class };
    }

    @Override
    public int[] getIePresence() {
        return new int[] { 2, 0, 2, 0, 0, 0, 0 };
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
        return "Paging";
    }

    @Override
    public String getXmlTagName() {
        return "Paging";
    }

}
