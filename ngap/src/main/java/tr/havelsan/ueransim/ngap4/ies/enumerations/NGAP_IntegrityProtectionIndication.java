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

public class NGAP_IntegrityProtectionIndication extends NgapEnumerated {

    public static final NGAP_IntegrityProtectionIndication REQUIRED = new NGAP_IntegrityProtectionIndication("required");
    public static final NGAP_IntegrityProtectionIndication PREFERRED = new NGAP_IntegrityProtectionIndication("preferred");
    public static final NGAP_IntegrityProtectionIndication NOT_NEEDED = new NGAP_IntegrityProtectionIndication("not-needed");

    public NGAP_IntegrityProtectionIndication(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "IntegrityProtectionIndication";
    }

    @Override
    public String getXmlTagName() {
        return "IntegrityProtectionIndication";
    }
}
