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

public class NGAP_CauseTransport extends NGAP_Enumerated {

    public static final NGAP_CauseTransport TRANSPORT_RESOURCE_UNAVAILABLE = new NGAP_CauseTransport("transport-resource-unavailable");
    public static final NGAP_CauseTransport UNSPECIFIED = new NGAP_CauseTransport("unspecified");

    protected NGAP_CauseTransport(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "CauseTransport";
    }

    @Override
    public String getXmlTagName() {
        return "CauseTransport";
    }
}
