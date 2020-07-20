package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

public class NGAP_UEPresenceInAreaOfInterestItem extends NGAP_Sequence {

    public NGAP_LocationReportingReferenceID locationReportingReferenceID;
    public NGAP_UEPresence uEPresence;

    @Override
    public String getAsnName() {
        return "UEPresenceInAreaOfInterestItem";
    }

    @Override
    public String getXmlTagName() {
        return "UEPresenceInAreaOfInterestItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"locationReportingReferenceID", "uEPresence"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"locationReportingReferenceID", "uEPresence"};
    }
}
