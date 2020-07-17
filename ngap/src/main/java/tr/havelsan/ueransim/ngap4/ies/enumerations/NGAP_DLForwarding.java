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

public class NGAP_DLForwarding extends NgapEnumerated {

    public static final NGAP_DLForwarding DL_FORWARDING_PROPOSED = new NGAP_DLForwarding("dl-forwarding-proposed");

    public NGAP_DLForwarding(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "DLForwarding";
    }

    @Override
    public String getXmlTagName() {
        return "DLForwarding";
    }
}
