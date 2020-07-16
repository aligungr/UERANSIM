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

public class NGAP_UEPresence extends NgapEnumerated {

    public static final NGAP_UEPresence IN = new NGAP_UEPresence("in");
    public static final NGAP_UEPresence OUT = new NGAP_UEPresence("out");
    public static final NGAP_UEPresence UNKNOWN = new NGAP_UEPresence("unknown");

    protected NGAP_UEPresence(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "UEPresence";
    }

    @Override
    protected String getXmlTagName() {
        return "UEPresence";
    }
}
