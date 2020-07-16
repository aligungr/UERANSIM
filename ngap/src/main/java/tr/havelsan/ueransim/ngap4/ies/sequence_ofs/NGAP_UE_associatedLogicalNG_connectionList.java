package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_UE_associatedLogicalNG_connectionItem;

public class NGAP_UE_associatedLogicalNG_connectionList extends NgapSequenceOf<NGAP_UE_associatedLogicalNG_connectionItem> {

    @Override
    protected String getAsnName() {
        return "UE-associatedLogicalNG-connectionList";
    }

    @Override
    protected String getXmlTagName() {
        return "UE-associatedLogicalNG-connectionList";
    }

    @Override
    public Class<NGAP_UE_associatedLogicalNG_connectionItem> getItemType() {
        return NGAP_UE_associatedLogicalNG_connectionItem.class;
    }
}
