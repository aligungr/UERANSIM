package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_UEPresence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_LocationReportingReferenceID;

public class NGAP_UEPresenceInAreaOfInterestItem extends NgapSequence {

    public NGAP_LocationReportingReferenceID locationReportingReferenceID;
    public NGAP_UEPresence uEPresence;

    @Override
    protected String getAsnName() {
        return "UEPresenceInAreaOfInterestItem";
    }

    @Override
    protected String getXmlTagName() {
        return "UEPresenceInAreaOfInterestItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"locationReportingReferenceID", "uEPresence"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"locationReportingReferenceID", "uEPresence"};
    }
}
