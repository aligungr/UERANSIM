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
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_MaskedIMEISV;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_Cause;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_HandoverType;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_NewSecurityContextInd;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_RRCInactiveTransitionReportRequest;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_RedirectionVoiceFallback;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_AMF_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_NAS_PDU;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_SourceToTarget_TransparentContainer;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_AllowedNSSAI;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_PDUSessionResourceSetupListHOReq;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

public class NGAP_HandoverRequest extends NGAP_BaseMessage {

    public NGAP_HandoverRequest() {

    }

    @Override
    public NgapMessageType getMessageType() {
        return NgapMessageType.HandoverRequest;
    }

    @Override
    public NgapProtocolIeType getProtocolIeType() {
        return NgapProtocolIeType.forMessage("HandoverRequest");
    }

    @Override
    public int getCriticality() {
        return 0;
    }

    @Override
    public int getProcedureCode() {
        return 13;
    }

    @Override
    public int getPduType() {
        return 0;
    }

    @Override
    public int[] getIeId() {
        return new int[] { 10, 29, 15, 110, 18, 119, 93, 41, 37, 73, 0, 108, 34, 101, 36, 33, 91, 28, 146, 165 };
    }

    @Override
    public int[] getIeCriticality() {
        return new int[] { 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1 };
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[] { NGAP_AMF_UE_NGAP_ID.class, NGAP_HandoverType.class, NGAP_Cause.class, NGAP_UEAggregateMaximumBitRate.class, NGAP_CoreNetworkAssistanceInformationForInactive.class, NGAP_UESecurityCapabilities.class, NGAP_SecurityContext.class, NGAP_NewSecurityContextInd.class, NGAP_NAS_PDU.class, NGAP_PDUSessionResourceSetupListHOReq.class, NGAP_AllowedNSSAI.class, NGAP_TraceActivation.class, NGAP_MaskedIMEISV.class, NGAP_SourceToTarget_TransparentContainer.class, NGAP_MobilityRestrictionList.class, NGAP_LocationReportingRequestType.class, NGAP_RRCInactiveTransitionReportRequest.class, NGAP_GUAMI.class, NGAP_RedirectionVoiceFallback.class, NGAP_CNAssistedRANTuning.class };
    }

    @Override
    public int[] getIePresence() {
        return new int[] { 2, 2, 2, 2, 0, 2, 2, 0, 0, 2, 2, 0, 0, 2, 0, 0, 0, 2, 0, 0 };
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
        return "HandoverRequest";
    }

    @Override
    public String getXmlTagName() {
        return "HandoverRequest";
    }

}
