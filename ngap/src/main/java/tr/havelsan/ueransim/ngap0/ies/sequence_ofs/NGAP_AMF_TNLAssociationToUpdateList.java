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

public class NGAP_AMF_TNLAssociationToUpdateList extends NGAP_SequenceOf<NGAP_AMF_TNLAssociationToUpdateItem> {

    public NGAP_AMF_TNLAssociationToUpdateList() {
        super();
    }

    public NGAP_AMF_TNLAssociationToUpdateList(List<NGAP_AMF_TNLAssociationToUpdateItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "AMF-TNLAssociationToUpdateList";
    }

    @Override
    public String getXmlTagName() {
        return "AMF-TNLAssociationToUpdateList";
    }

    @Override
    public Class<NGAP_AMF_TNLAssociationToUpdateItem> getItemType() {
        return NGAP_AMF_TNLAssociationToUpdateItem.class;
    }
}
