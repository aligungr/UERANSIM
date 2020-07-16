package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_RRCInactiveTransitionReportRequest extends NgapEnumerated {

    public static final NGAP_RRCInactiveTransitionReportRequest SUBSEQUENT_STATE_TRANSITION_REPORT = new NGAP_RRCInactiveTransitionReportRequest("subsequent-state-transition-report");
    public static final NGAP_RRCInactiveTransitionReportRequest SINGLE_RRC_CONNECTED_STATE_REPORT = new NGAP_RRCInactiveTransitionReportRequest("single-rrc-connected-state-report");
    public static final NGAP_RRCInactiveTransitionReportRequest CANCEL_REPORT = new NGAP_RRCInactiveTransitionReportRequest("cancel-report");

    protected NGAP_RRCInactiveTransitionReportRequest(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "RRCInactiveTransitionReportRequest";
    }

    @Override
    protected String getXmlTagName() {
        return "RRCInactiveTransitionReportRequest";
    }
}
