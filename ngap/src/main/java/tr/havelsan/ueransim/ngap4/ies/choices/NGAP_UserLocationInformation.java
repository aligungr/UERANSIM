package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap4.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap4.ies.sequences.*;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap4.ies.choices.*;
import tr.havelsan.ueransim.ngap4.ies.integers.*;
import tr.havelsan.ueransim.ngap4.ies.enumerations.*;

import java.util.List;

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
