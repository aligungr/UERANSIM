package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_EventType;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_ReportArea;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_LocationReportingReferenceID;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_AreaOfInterestList;

public class NGAP_LocationReportingRequestType extends NgapSequence {

    public NGAP_EventType eventType;
    public NGAP_ReportArea reportArea;
    public NGAP_AreaOfInterestList areaOfInterestList;
    public NGAP_LocationReportingReferenceID locationReportingReferenceIDToBeCancelled;

    @Override
    protected String getAsnName() {
        return "LocationReportingRequestType";
    }

    @Override
    protected String getXmlTagName() {
        return "LocationReportingRequestType";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"eventType", "reportArea", "areaOfInterestList", "locationReportingReferenceIDToBeCancelled"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"eventType", "reportArea", "areaOfInterestList", "locationReportingReferenceIDToBeCancelled"};
    }
}
