package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_TNLAssociationList extends NGAP_SequenceOf<NGAP_TNLAssociationItem> {

    public NGAP_TNLAssociationList() {
        super();
    }

    public NGAP_TNLAssociationList(List<NGAP_TNLAssociationItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "TNLAssociationList";
    }

    @Override
    public String getXmlTagName() {
        return "TNLAssociationList";
    }

    @Override
    public Class<NGAP_TNLAssociationItem> getItemType() {
        return NGAP_TNLAssociationItem.class;
    }
}
