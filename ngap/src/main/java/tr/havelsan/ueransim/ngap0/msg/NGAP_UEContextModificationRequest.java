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

public class NGAP_UEContextModificationRequest extends NGAP_BaseMessage {

    public NGAP_UEContextModificationRequest() {

    }

    @Override
    public NgapMessageType getMessageType() {
        return NgapMessageType.UEContextModificationRequest;
    }

    @Override
    public NgapProtocolIeType getProtocolIeType() {
        return NgapProtocolIeType.forMessage("UEContextModificationRequest");
    }

    @Override
    public int getCriticality() {
        return 0;
    }

    @Override
    public int getProcedureCode() {
        return 40;
    }

    @Override
    public int getPduType() {
        return 0;
    }

    @Override
    public int[] getIeId() {
        return new int[] { 10, 85, 83, 94, 31, 110, 119, 18, 24, 40, 91, 162, 165 };
    }

    @Override
    public int[] getIeCriticality() {
        return new int[] { 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1 };
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[] { NGAP_AMF_UE_NGAP_ID.class, NGAP_RAN_UE_NGAP_ID.class, NGAP_RANPagingPriority.class, NGAP_SecurityKey.class, NGAP_IndexToRFSP.class, NGAP_UEAggregateMaximumBitRate.class, NGAP_UESecurityCapabilities.class, NGAP_CoreNetworkAssistanceInformationForInactive.class, NGAP_EmergencyFallbackIndicator.class, NGAP_AMF_UE_NGAP_ID.class, NGAP_RRCInactiveTransitionReportRequest.class, NGAP_GUAMI.class, NGAP_CNAssistedRANTuning.class };
    }

    @Override
    public int[] getIePresence() {
        return new int[] { 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
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
        return "UEContextModificationRequest";
    }

    @Override
    public String getXmlTagName() {
        return "UEContextModificationRequest";
    }

}
