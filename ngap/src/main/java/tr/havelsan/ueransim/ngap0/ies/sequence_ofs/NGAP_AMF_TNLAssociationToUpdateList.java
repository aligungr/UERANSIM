package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

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
