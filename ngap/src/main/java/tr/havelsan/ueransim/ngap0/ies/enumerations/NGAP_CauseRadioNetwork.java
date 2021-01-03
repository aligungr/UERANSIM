/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.NGAP_Enumerated;

public class NGAP_CauseRadioNetwork extends NGAP_Enumerated {

    public static final NGAP_CauseRadioNetwork UNSPECIFIED = new NGAP_CauseRadioNetwork("unspecified");
    public static final NGAP_CauseRadioNetwork TXNRELOCOVERALL_EXPIRY = new NGAP_CauseRadioNetwork("txnrelocoverall-expiry");
    public static final NGAP_CauseRadioNetwork SUCCESSFUL_HANDOVER = new NGAP_CauseRadioNetwork("successful-handover");
    public static final NGAP_CauseRadioNetwork RELEASE_DUE_TO_NGRAN_GENERATED_REASON = new NGAP_CauseRadioNetwork("release-due-to-ngran-generated-reason");
    public static final NGAP_CauseRadioNetwork RELEASE_DUE_TO_5GC_GENERATED_REASON = new NGAP_CauseRadioNetwork("release-due-to-5gc-generated-reason");
    public static final NGAP_CauseRadioNetwork HANDOVER_CANCELLED = new NGAP_CauseRadioNetwork("handover-cancelled");
    public static final NGAP_CauseRadioNetwork PARTIAL_HANDOVER = new NGAP_CauseRadioNetwork("partial-handover");
    public static final NGAP_CauseRadioNetwork HO_FAILURE_IN_TARGET_5GC_NGRAN_NODE_OR_TARGET_SYSTEM = new NGAP_CauseRadioNetwork("ho-failure-in-target-5GC-ngran-node-or-target-system");
    public static final NGAP_CauseRadioNetwork HO_TARGET_NOT_ALLOWED = new NGAP_CauseRadioNetwork("ho-target-not-allowed");
    public static final NGAP_CauseRadioNetwork TNGRELOCOVERALL_EXPIRY = new NGAP_CauseRadioNetwork("tngrelocoverall-expiry");
    public static final NGAP_CauseRadioNetwork TNGRELOCPREP_EXPIRY = new NGAP_CauseRadioNetwork("tngrelocprep-expiry");
    public static final NGAP_CauseRadioNetwork CELL_NOT_AVAILABLE = new NGAP_CauseRadioNetwork("cell-not-available");
    public static final NGAP_CauseRadioNetwork UNKNOWN_TARGETID = new NGAP_CauseRadioNetwork("unknown-targetID");
    public static final NGAP_CauseRadioNetwork NO_RADIO_RESOURCES_AVAILABLE_IN_TARGET_CELL = new NGAP_CauseRadioNetwork("no-radio-resources-available-in-target-cell");
    public static final NGAP_CauseRadioNetwork UNKNOWN_LOCAL_UE_NGAP_ID = new NGAP_CauseRadioNetwork("unknown-local-UE-NGAP-ID");
    public static final NGAP_CauseRadioNetwork INCONSISTENT_REMOTE_UE_NGAP_ID = new NGAP_CauseRadioNetwork("inconsistent-remote-UE-NGAP-ID");
    public static final NGAP_CauseRadioNetwork HANDOVER_DESIRABLE_FOR_RADIO_REASON = new NGAP_CauseRadioNetwork("handover-desirable-for-radio-reason");
    public static final NGAP_CauseRadioNetwork TIME_CRITICAL_HANDOVER = new NGAP_CauseRadioNetwork("time-critical-handover");
    public static final NGAP_CauseRadioNetwork RESOURCE_OPTIMISATION_HANDOVER = new NGAP_CauseRadioNetwork("resource-optimisation-handover");
    public static final NGAP_CauseRadioNetwork REDUCE_LOAD_IN_SERVING_CELL = new NGAP_CauseRadioNetwork("reduce-load-in-serving-cell");
    public static final NGAP_CauseRadioNetwork USER_INACTIVITY = new NGAP_CauseRadioNetwork("user-inactivity");
    public static final NGAP_CauseRadioNetwork RADIO_CONNECTION_WITH_UE_LOST = new NGAP_CauseRadioNetwork("radio-connection-with-ue-lost");
    public static final NGAP_CauseRadioNetwork RADIO_RESOURCES_NOT_AVAILABLE = new NGAP_CauseRadioNetwork("radio-resources-not-available");
    public static final NGAP_CauseRadioNetwork INVALID_QOS_COMBINATION = new NGAP_CauseRadioNetwork("invalid-qos-combination");
    public static final NGAP_CauseRadioNetwork FAILURE_IN_RADIO_INTERFACE_PROCEDURE = new NGAP_CauseRadioNetwork("failure-in-radio-interface-procedure");
    public static final NGAP_CauseRadioNetwork INTERACTION_WITH_OTHER_PROCEDURE = new NGAP_CauseRadioNetwork("interaction-with-other-procedure");
    public static final NGAP_CauseRadioNetwork UNKNOWN_PDU_SESSION_ID = new NGAP_CauseRadioNetwork("unknown-PDU-session-ID");
    public static final NGAP_CauseRadioNetwork UNKOWN_QOS_FLOW_ID = new NGAP_CauseRadioNetwork("unkown-qos-flow-ID");
    public static final NGAP_CauseRadioNetwork MULTIPLE_PDU_SESSION_ID_INSTANCES = new NGAP_CauseRadioNetwork("multiple-PDU-session-ID-instances");
    public static final NGAP_CauseRadioNetwork MULTIPLE_QOS_FLOW_ID_INSTANCES = new NGAP_CauseRadioNetwork("multiple-qos-flow-ID-instances");
    public static final NGAP_CauseRadioNetwork ENCRYPTION_AND_OR_INTEGRITY_PROTECTION_ALGORITHMS_NOT_SUPPORTED = new NGAP_CauseRadioNetwork("encryption-and-or-integrity-protection-algorithms-not-supported");
    public static final NGAP_CauseRadioNetwork NG_INTRA_SYSTEM_HANDOVER_TRIGGERED = new NGAP_CauseRadioNetwork("ng-intra-system-handover-triggered");
    public static final NGAP_CauseRadioNetwork NG_INTER_SYSTEM_HANDOVER_TRIGGERED = new NGAP_CauseRadioNetwork("ng-inter-system-handover-triggered");
    public static final NGAP_CauseRadioNetwork XN_HANDOVER_TRIGGERED = new NGAP_CauseRadioNetwork("xn-handover-triggered");
    public static final NGAP_CauseRadioNetwork NOT_SUPPORTED_5QI_VALUE = new NGAP_CauseRadioNetwork("not-supported-5QI-value");
    public static final NGAP_CauseRadioNetwork UE_CONTEXT_TRANSFER = new NGAP_CauseRadioNetwork("ue-context-transfer");
    public static final NGAP_CauseRadioNetwork IMS_VOICE_EPS_FALLBACK_OR_RAT_FALLBACK_TRIGGERED = new NGAP_CauseRadioNetwork("ims-voice-eps-fallback-or-rat-fallback-triggered");
    public static final NGAP_CauseRadioNetwork UP_INTEGRITY_PROTECTION_NOT_POSSIBLE = new NGAP_CauseRadioNetwork("up-integrity-protection-not-possible");
    public static final NGAP_CauseRadioNetwork UP_CONFIDENTIALITY_PROTECTION_NOT_POSSIBLE = new NGAP_CauseRadioNetwork("up-confidentiality-protection-not-possible");
    public static final NGAP_CauseRadioNetwork SLICE_NOT_SUPPORTED = new NGAP_CauseRadioNetwork("slice-not-supported");
    public static final NGAP_CauseRadioNetwork UE_IN_RRC_INACTIVE_STATE_NOT_REACHABLE = new NGAP_CauseRadioNetwork("ue-in-rrc-inactive-state-not-reachable");
    public static final NGAP_CauseRadioNetwork REDIRECTION = new NGAP_CauseRadioNetwork("redirection");
    public static final NGAP_CauseRadioNetwork RESOURCES_NOT_AVAILABLE_FOR_THE_SLICE = new NGAP_CauseRadioNetwork("resources-not-available-for-the-slice");
    public static final NGAP_CauseRadioNetwork UE_MAX_INTEGRITY_PROTECTED_DATA_RATE_REASON = new NGAP_CauseRadioNetwork("ue-max-integrity-protected-data-rate-reason");
    public static final NGAP_CauseRadioNetwork RELEASE_DUE_TO_CN_DETECTED_MOBILITY = new NGAP_CauseRadioNetwork("release-due-to-cn-detected-mobility");
    public static final NGAP_CauseRadioNetwork N26_INTERFACE_NOT_AVAILABLE = new NGAP_CauseRadioNetwork("n26-interface-not-available");
    public static final NGAP_CauseRadioNetwork RELEASE_DUE_TO_PRE_EMPTION = new NGAP_CauseRadioNetwork("release-due-to-pre-emption");
    public static final NGAP_CauseRadioNetwork MULTIPLE_LOCATION_REPORTING_REFERENCE_ID_INSTANCES = new NGAP_CauseRadioNetwork("multiple-location-reporting-reference-ID-instances");

    protected NGAP_CauseRadioNetwork(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "CauseRadioNetwork";
    }

    @Override
    public String getXmlTagName() {
        return "CauseRadioNetwork";
    }
}
