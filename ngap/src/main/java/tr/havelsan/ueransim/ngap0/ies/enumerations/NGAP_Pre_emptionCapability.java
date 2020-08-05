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

public class NGAP_Pre_emptionCapability extends NGAP_Enumerated {

    public static final NGAP_Pre_emptionCapability SHALL_NOT_TRIGGER_PRE_EMPTION = new NGAP_Pre_emptionCapability("shall-not-trigger-pre-emption");
    public static final NGAP_Pre_emptionCapability MAY_TRIGGER_PRE_EMPTION = new NGAP_Pre_emptionCapability("may-trigger-pre-emption");

    protected NGAP_Pre_emptionCapability(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "Pre-emptionCapability";
    }

    @Override
    public String getXmlTagName() {
        return "Pre-emptionCapability";
    }
}
