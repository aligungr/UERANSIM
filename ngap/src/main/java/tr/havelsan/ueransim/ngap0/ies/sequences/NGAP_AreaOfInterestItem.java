package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_AreaOfInterestItem extends NGAP_Sequence {

    public NGAP_AreaOfInterest areaOfInterest;
    public NGAP_LocationReportingReferenceID locationReportingReferenceID;

    @Override
    public String getAsnName() {
        return "AreaOfInterestItem";
    }

    @Override
    public String getXmlTagName() {
        return "AreaOfInterestItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"areaOfInterest", "locationReportingReferenceID"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"areaOfInterest", "locationReportingReferenceID"};
    }
}
