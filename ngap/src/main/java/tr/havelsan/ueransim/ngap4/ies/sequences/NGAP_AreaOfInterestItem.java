package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_LocationReportingReferenceID;

public class NGAP_AreaOfInterestItem extends NgapSequence {

    public NGAP_AreaOfInterest areaOfInterest;
    public NGAP_LocationReportingReferenceID locationReportingReferenceID;

    @Override
    protected String getAsnName() {
        return "AreaOfInterestItem";
    }

    @Override
    protected String getXmlTagName() {
        return "AreaOfInterestItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"areaOfInterest", "locationReportingReferenceID"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"areaOfInterest", "locationReportingReferenceID"};
    }
}
