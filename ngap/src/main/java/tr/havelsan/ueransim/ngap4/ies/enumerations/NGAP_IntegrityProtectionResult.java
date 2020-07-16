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

public class NGAP_IntegrityProtectionResult extends NgapEnumerated {

    public static final NGAP_IntegrityProtectionResult PERFORMED = new NGAP_IntegrityProtectionResult("performed");
    public static final NGAP_IntegrityProtectionResult NOT_PERFORMED = new NGAP_IntegrityProtectionResult("not-performed");

    protected NGAP_IntegrityProtectionResult(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "IntegrityProtectionResult";
    }

    @Override
    protected String getXmlTagName() {
        return "IntegrityProtectionResult";
    }
}
