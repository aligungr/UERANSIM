package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_AMF_TNLAssociationToRemoveItem;

public class NGAP_AMF_TNLAssociationToRemoveList extends NgapSequenceOf<NGAP_AMF_TNLAssociationToRemoveItem> {

    @Override
    protected String getAsnName() {
        return "AMF-TNLAssociationToRemoveList";
    }

    @Override
    protected String getXmlTagName() {
        return "AMF-TNLAssociationToRemoveList";
    }

    @Override
    public Class<NGAP_AMF_TNLAssociationToRemoveItem> getItemType() {
        return NGAP_AMF_TNLAssociationToRemoveItem.class;
    }
}
