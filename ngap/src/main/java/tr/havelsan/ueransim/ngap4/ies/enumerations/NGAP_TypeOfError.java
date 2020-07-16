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

public class NGAP_TypeOfError extends NgapEnumerated {

    public static final NGAP_TypeOfError NOT_UNDERSTOOD = new NGAP_TypeOfError("not-understood");
    public static final NGAP_TypeOfError MISSING = new NGAP_TypeOfError("missing");

    protected NGAP_TypeOfError(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "TypeOfError";
    }

    @Override
    protected String getXmlTagName() {
        return "TypeOfError";
    }
}
