package tr.havelsan.ueransim.ngap4.msg;

import tr.havelsan.ueransim.ngap4.pdu.*;
import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.ngap2.NgapMessageType;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap4.ies.sequences.*;
import tr.havelsan.ueransim.ngap4.ies.choices.*;
import tr.havelsan.ueransim.ngap4.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap4.ies.integers.*;
import tr.havelsan.ueransim.ngap4.ies.enumerations.*;

public class NGAP_InitialContextSetupRequest extends NGAP_BaseMessage {

    public NGAP_ProtocolIEContainer protocolIEs;

    public NGAP_InitialContextSetupRequest() {

    }

    @Override
    public int getCriticality() {
        return 0;
    }

    @Override
    public int getProcedureCode() {
        return 14;
    }

    @Override
    public int getPduType() {
        return 0;
    }

    @Override
    public int[] getIeId() {
        return new int[]{10, 85, 48, 110, 18, 28, 71, 0, 119, 94, 108, 36, 117, 31, 34, 38, 24, 91, 118};
    }

    @Override
    public int[] getIeCriticality() {
        return new int[]{0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1};
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[]{NGAP_AMF_UE_NGAP_ID.class, NGAP_RAN_UE_NGAP_ID.class, NGAP_AMFName.class, NGAP_UEAggregateMaximumBitRate.class, NGAP_CoreNetworkAssistanceInformation.class, NGAP_GUAMI.class, NGAP_PDUSessionResourceSetupListCxtReq.class, NGAP_AllowedNSSAI.class, NGAP_UESecurityCapabilities.class, NGAP_SecurityKey.class, NGAP_TraceActivation.class, NGAP_MobilityRestrictionList.class, NGAP_UERadioCapability.class, NGAP_IndexToRFSP.class, NGAP_MaskedIMEISV.class, NGAP_NAS_PDU.class, NGAP_EmergencyFallbackIndicator.class, NGAP_RRCInactiveTransitionReportRequest.class, NGAP_UERadioCapabilityForPaging.class};
    }

    @Override
    public int[] getIePresence() {
        return new int[]{2, 2, 0, 1, 0, 2, 0, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"protocolIEs"};
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"protocolIEs"};
    }

    @Override
    public String getAsnName() {
        return "InitialContextSetupRequest";
    }

    @Override
    public String getXmlTagName() {
        return "InitialContextSetupRequest";
    }

}
