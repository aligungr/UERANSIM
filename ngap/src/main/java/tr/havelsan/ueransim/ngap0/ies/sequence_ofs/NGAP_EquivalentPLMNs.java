package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

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
