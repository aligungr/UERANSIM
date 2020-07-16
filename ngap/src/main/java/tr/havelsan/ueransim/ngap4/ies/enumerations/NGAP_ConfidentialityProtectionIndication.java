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

public class NGAP_ConfidentialityProtectionIndication extends NgapEnumerated {

    public static final NGAP_ConfidentialityProtectionIndication REQUIRED = new NGAP_ConfidentialityProtectionIndication("required");
    public static final NGAP_ConfidentialityProtectionIndication PREFERRED = new NGAP_ConfidentialityProtectionIndication("preferred");
    public static final NGAP_ConfidentialityProtectionIndication NOT_NEEDED = new NGAP_ConfidentialityProtectionIndication("not-needed");

    protected NGAP_ConfidentialityProtectionIndication(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "ConfidentialityProtectionIndication";
    }

    @Override
    protected String getXmlTagName() {
        return "ConfidentialityProtectionIndication";
    }
}
