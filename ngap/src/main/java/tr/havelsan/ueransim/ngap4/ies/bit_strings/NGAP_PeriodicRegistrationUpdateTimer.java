package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;

public class NGAP_PeriodicRegistrationUpdateTimer extends NgapBitString {

    @Override
    protected String getAsnName() {
        return "PeriodicRegistrationUpdateTimer";
    }

    @Override
    protected String getXmlTagName() {
        return "PeriodicRegistrationUpdateTimer";
    }
}
