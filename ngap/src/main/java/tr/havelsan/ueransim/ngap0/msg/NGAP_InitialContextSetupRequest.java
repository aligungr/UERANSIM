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
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_SecurityKey;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_RRCInactiveTransitionReportRequest;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_RedirectionVoiceFallback;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_AMF_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_IndexToRFSP;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_RAN_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_NAS_PDU;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_UERadioCapability;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.NGAP_AMFName;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_AllowedNSSAI;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_PDUSessionResourceSetupListCxtReq;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

public class NGAP_InitialContextSetupRequest extends NGAP_BaseMessage {

    public NGAP_InitialContextSetupRequest() {

    }

    @Override
    public NgapMessageType getMessageType() {
        return NgapMessageType.InitialContextSetupRequest;
    }

    @Override
    public NgapProtocolIeType getProtocolIeType() {
        return NgapProtocolIeType.forMessage("InitialContextSetupRequest");
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
        return new int[] { 10, 85, 48, 110, 18, 28, 71, 0, 119, 94, 108, 36, 117, 31, 34, 38, 24, 91, 118, 146, 33, 165 };
    }

    @Override
    public int[] getIeCriticality() {
        return new int[] { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1 };
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[] { NGAP_AMF_UE_NGAP_ID.class, NGAP_RAN_UE_NGAP_ID.class, NGAP_AMFName.class, NGAP_UEAggregateMaximumBitRate.class, NGAP_CoreNetworkAssistanceInformationForInactive.class, NGAP_GUAMI.class, NGAP_PDUSessionResourceSetupListCxtReq.class, NGAP_AllowedNSSAI.class, NGAP_UESecurityCapabilities.class, NGAP_SecurityKey.class, NGAP_TraceActivation.class, NGAP_MobilityRestrictionList.class, NGAP_UERadioCapability.class, NGAP_IndexToRFSP.class, NGAP_MaskedIMEISV.class, NGAP_NAS_PDU.class, NGAP_EmergencyFallbackIndicator.class, NGAP_RRCInactiveTransitionReportRequest.class, NGAP_UERadioCapabilityForPaging.class, NGAP_RedirectionVoiceFallback.class, NGAP_LocationReportingRequestType.class, NGAP_CNAssistedRANTuning.class };
    }

    @Override
    public int[] getIePresence() {
        return new int[] { 2, 2, 0, 1, 0, 2, 0, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
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
        return "InitialContextSetupRequest";
    }

    @Override
    public String getXmlTagName() {
        return "InitialContextSetupRequest";
    }

}
