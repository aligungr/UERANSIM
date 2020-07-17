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

public class NGAP_AMF_TNLAssociationToRemoveList extends NGAP_SequenceOf<NGAP_AMF_TNLAssociationToRemoveItem> {

    public NGAP_AMF_TNLAssociationToRemoveList() {
        super();
    }

    public NGAP_AMF_TNLAssociationToRemoveList(List<NGAP_AMF_TNLAssociationToRemoveItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "AMF-TNLAssociationToRemoveList";
    }

    @Override
    public String getXmlTagName() {
        return "AMF-TNLAssociationToRemoveList";
    }

    @Override
    public Class<NGAP_AMF_TNLAssociationToRemoveItem> getItemType() {
        return NGAP_AMF_TNLAssociationToRemoveItem.class;
    }
}
