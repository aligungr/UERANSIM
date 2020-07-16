package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.NgapChoice;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_UserLocationInformationEUTRA;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_UserLocationInformationN3IWF;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_UserLocationInformationNR;

public class NGAP_UserLocationInformation extends NgapChoice {

    public NGAP_UserLocationInformationEUTRA userLocationInformationEUTRA;
    public NGAP_UserLocationInformationNR userLocationInformationNR;
    public NGAP_UserLocationInformationN3IWF userLocationInformationN3IWF;

    @Override
    protected String getAsnName() {
        return "UserLocationInformation";
    }

    @Override
    protected String getXmlTagName() {
        return "UserLocationInformation";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"userLocationInformationEUTRA", "userLocationInformationNR", "userLocationInformationN3IWF"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"userLocationInformationEUTRA", "userLocationInformationNR", "userLocationInformationN3IWF"};
    }
}
