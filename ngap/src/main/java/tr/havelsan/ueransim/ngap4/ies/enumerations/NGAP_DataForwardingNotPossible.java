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

public class NGAP_DataForwardingNotPossible extends NgapEnumerated {

    public static final NGAP_DataForwardingNotPossible DATA_FORWARDING_NOT_POSSIBLE = new NGAP_DataForwardingNotPossible("data-forwarding-not-possible");

    public NGAP_DataForwardingNotPossible(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "DataForwardingNotPossible";
    }

    @Override
    public String getXmlTagName() {
        return "DataForwardingNotPossible";
    }
}
