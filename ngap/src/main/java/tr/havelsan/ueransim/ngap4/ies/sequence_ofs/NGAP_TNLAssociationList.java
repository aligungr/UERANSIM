package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_TNLAssociationItem;

public class NGAP_TNLAssociationList extends NgapSequenceOf<NGAP_TNLAssociationItem> {

    @Override
    protected String getAsnName() {
        return "TNLAssociationList";
    }

    @Override
    protected String getXmlTagName() {
        return "TNLAssociationList";
    }

    @Override
    public Class<NGAP_TNLAssociationItem> getItemType() {
        return NGAP_TNLAssociationItem.class;
    }
}
