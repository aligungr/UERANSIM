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

public class NGAP_Pre_emptionCapability extends NgapEnumerated {

    public static final NGAP_Pre_emptionCapability SHALL_NOT_TRIGGER_PRE_EMPTION = new NGAP_Pre_emptionCapability("shall-not-trigger-pre-emption");
    public static final NGAP_Pre_emptionCapability MAY_TRIGGER_PRE_EMPTION = new NGAP_Pre_emptionCapability("may-trigger-pre-emption");

    protected NGAP_Pre_emptionCapability(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "Pre-emptionCapability";
    }

    @Override
    protected String getXmlTagName() {
        return "Pre-emptionCapability";
    }
}
