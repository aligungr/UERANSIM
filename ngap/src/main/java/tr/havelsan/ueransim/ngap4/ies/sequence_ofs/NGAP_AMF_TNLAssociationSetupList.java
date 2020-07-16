package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_AMF_TNLAssociationSetupItem;

public class NGAP_AMF_TNLAssociationSetupList extends NgapSequenceOf<NGAP_AMF_TNLAssociationSetupItem> {

    @Override
    protected String getAsnName() {
        return "AMF-TNLAssociationSetupList";
    }

    @Override
    protected String getXmlTagName() {
        return "AMF-TNLAssociationSetupList";
    }

    @Override
    public Class<NGAP_AMF_TNLAssociationSetupItem> getItemType() {
        return NGAP_AMF_TNLAssociationSetupItem.class;
    }
}
