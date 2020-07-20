package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

public class NGAP_UserLocationInformation extends NGAP_Choice {

    public NGAP_UserLocationInformationEUTRA userLocationInformationEUTRA;
    public NGAP_UserLocationInformationNR userLocationInformationNR;
    public NGAP_UserLocationInformationN3IWF userLocationInformationN3IWF;

    @Override
    public String getAsnName() {
        return "UserLocationInformation";
    }

    @Override
    public String getXmlTagName() {
        return "UserLocationInformation";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"userLocationInformationEUTRA", "userLocationInformationNR", "userLocationInformationN3IWF"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"userLocationInformationEUTRA", "userLocationInformationNR", "userLocationInformationN3IWF"};
    }
}
