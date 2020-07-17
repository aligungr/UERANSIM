package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.ngap4.pdu.*;
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

public class NGAP_EquivalentPLMNs extends NGAP_SequenceOf<NGAP_PLMNIdentity> {

    public NGAP_EquivalentPLMNs() {
        super();
    }

    public NGAP_EquivalentPLMNs(List<NGAP_PLMNIdentity> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "EquivalentPLMNs";
    }

    @Override
    public String getXmlTagName() {
        return "EquivalentPLMNs";
    }

    @Override
    public Class<NGAP_PLMNIdentity> getItemType() {
        return NGAP_PLMNIdentity.class;
    }
}
