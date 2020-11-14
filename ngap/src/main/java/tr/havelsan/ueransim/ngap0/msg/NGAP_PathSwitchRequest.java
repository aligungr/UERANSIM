/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.ngap0.msg;

import tr.havelsan.ueransim.ngap0.pdu.*;
import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

public class NGAP_PathSwitchRequest extends NGAP_BaseMessage {

    public NGAP_PathSwitchRequest() {

    }

    @Override
    public NgapMessageType getMessageType() {
        return NgapMessageType.PathSwitchRequest;
    }

    @Override
    public NgapProtocolIeType getProtocolIeType() {
        return NgapProtocolIeType.forMessage("PathSwitchRequest");
    }

    @Override
    public int getCriticality() {
        return 0;
    }

    @Override
    public int getProcedureCode() {
        return 25;
    }

    @Override
    public int getPduType() {
        return 0;
    }

    @Override
    public int[] getIeId() {
        return new int[] { 85, 100, 121, 119, 76, 57 };
    }

    @Override
    public int[] getIeCriticality() {
        return new int[] { 0, 0, 1, 1, 0, 1 };
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[] { NGAP_RAN_UE_NGAP_ID.class, NGAP_AMF_UE_NGAP_ID.class, NGAP_UserLocationInformation.class, NGAP_UESecurityCapabilities.class, NGAP_PDUSessionResourceToBeSwitchedDLList.class, NGAP_PDUSessionResourceFailedToSetupListPSReq.class };
    }

    @Override
    public int[] getIePresence() {
        return new int[] { 2, 2, 2, 2, 2, 0 };
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
        return "PathSwitchRequest";
    }

    @Override
    public String getXmlTagName() {
        return "PathSwitchRequest";
    }

}
