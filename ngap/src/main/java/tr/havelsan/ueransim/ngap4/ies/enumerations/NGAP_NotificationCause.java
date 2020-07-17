package tr.havelsan.ueransim.ngap4.ies.enumerations;

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

public class NGAP_NotificationCause extends NGAP_Enumerated {

    public static final NGAP_NotificationCause FULFILLED = new NGAP_NotificationCause("fulfilled");
    public static final NGAP_NotificationCause NOT_FULFILLED = new NGAP_NotificationCause("not-fulfilled");

    protected NGAP_NotificationCause(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "NotificationCause";
    }

    @Override
    public String getXmlTagName() {
        return "NotificationCause";
    }
}
