package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_AMF_TNLAssociationToAddItem;

public class NGAP_AMF_TNLAssociationToAddList extends NgapSequenceOf<NGAP_AMF_TNLAssociationToAddItem> {

    @Override
    protected String getAsnName() {
        return "AMF-TNLAssociationToAddList";
    }

    @Override
    protected String getXmlTagName() {
        return "AMF-TNLAssociationToAddList";
    }

    @Override
    public Class<NGAP_AMF_TNLAssociationToAddItem> getItemType() {
        return NGAP_AMF_TNLAssociationToAddItem.class;
    }
}
