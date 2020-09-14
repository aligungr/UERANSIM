package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.pdu.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

import java.util.List;

public class NGAP_UEPresence extends NGAP_Enumerated {

    public static final NGAP_UEPresence IN = new NGAP_UEPresence("in");
    public static final NGAP_UEPresence OUT = new NGAP_UEPresence("out");
    public static final NGAP_UEPresence UNKNOWN = new NGAP_UEPresence("unknown");

    protected NGAP_UEPresence(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "UEPresence";
    }

    @Override
    public String getXmlTagName() {
        return "UEPresence";
    }
}
