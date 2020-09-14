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

public class NGAP_PDUSessionResourceReleaseCommand extends NGAP_BaseMessage {

    public NGAP_PDUSessionResourceReleaseCommand() {

    }

    @Override
    public NgapMessageType getMessageType() {
        return NgapMessageType.PDUSessionResourceReleaseCommand;
    }

    @Override
    public NgapProtocolIeType getProtocolIeType() {
        return NgapProtocolIeType.forMessage("PDUSessionResourceReleaseCommand");
    }

    @Override
    public int getCriticality() {
        return 0;
    }

    @Override
    public int getProcedureCode() {
        return 28;
    }

    @Override
    public int getPduType() {
        return 0;
    }

    @Override
    public int[] getIeId() {
        return new int[] { 10, 85, 83, 38, 79 };
    }

    @Override
    public int[] getIeCriticality() {
        return new int[] { 0, 0, 1, 1, 0 };
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[] { NGAP_AMF_UE_NGAP_ID.class, NGAP_RAN_UE_NGAP_ID.class, NGAP_RANPagingPriority.class, NGAP_NAS_PDU.class, NGAP_PDUSessionResourceToReleaseListRelCmd.class };
    }

    @Override
    public int[] getIePresence() {
        return new int[] { 2, 2, 0, 0, 2 };
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
        return "PDUSessionResourceReleaseCommand";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceReleaseCommand";
    }

}
