package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

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

public class NGAP_PDUSessionResourceListHORqd extends NgapSequenceOf<NGAP_PDUSessionResourceItemHORqd> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceListHORqd";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceListHORqd";
    }

    @Override
    public Class<NGAP_PDUSessionResourceItemHORqd> getItemType() {
        return NGAP_PDUSessionResourceItemHORqd.class;
    }
}
