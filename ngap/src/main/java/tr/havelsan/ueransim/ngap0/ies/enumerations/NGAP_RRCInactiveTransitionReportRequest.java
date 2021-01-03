/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.NGAP_Enumerated;

public class NGAP_RRCInactiveTransitionReportRequest extends NGAP_Enumerated {

    public static final NGAP_RRCInactiveTransitionReportRequest SUBSEQUENT_STATE_TRANSITION_REPORT = new NGAP_RRCInactiveTransitionReportRequest("subsequent-state-transition-report");
    public static final NGAP_RRCInactiveTransitionReportRequest SINGLE_RRC_CONNECTED_STATE_REPORT = new NGAP_RRCInactiveTransitionReportRequest("single-rrc-connected-state-report");
    public static final NGAP_RRCInactiveTransitionReportRequest CANCEL_REPORT = new NGAP_RRCInactiveTransitionReportRequest("cancel-report");

    protected NGAP_RRCInactiveTransitionReportRequest(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "RRCInactiveTransitionReportRequest";
    }

    @Override
    public String getXmlTagName() {
        return "RRCInactiveTransitionReportRequest";
    }
}
