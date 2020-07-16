package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_AMF_TNLAssociationToUpdateItem;

public class NGAP_AMF_TNLAssociationToUpdateList extends NgapSequenceOf<NGAP_AMF_TNLAssociationToUpdateItem> {

    @Override
    protected String getAsnName() {
        return "AMF-TNLAssociationToUpdateList";
    }

    @Override
    protected String getXmlTagName() {
        return "AMF-TNLAssociationToUpdateList";
    }

    @Override
    public Class<NGAP_AMF_TNLAssociationToUpdateItem> getItemType() {
        return NGAP_AMF_TNLAssociationToUpdateItem.class;
    }
}
