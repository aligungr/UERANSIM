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

public class NGAP_RedirectionVoiceFallback extends NGAP_Enumerated {

    public static final NGAP_RedirectionVoiceFallback POSSIBLE = new NGAP_RedirectionVoiceFallback("possible");
    public static final NGAP_RedirectionVoiceFallback NOT_POSSIBLE = new NGAP_RedirectionVoiceFallback("not-possible");

    protected NGAP_RedirectionVoiceFallback(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "RedirectionVoiceFallback";
    }

    @Override
    public String getXmlTagName() {
        return "RedirectionVoiceFallback";
    }
}
