package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_UE_associatedLogicalNG_connectionList extends NGAP_SequenceOf<NGAP_UE_associatedLogicalNG_connectionItem> {

    public NGAP_UE_associatedLogicalNG_connectionList() {
        super();
    }

    public NGAP_UE_associatedLogicalNG_connectionList(List<NGAP_UE_associatedLogicalNG_connectionItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "UE-associatedLogicalNG-connectionList";
    }

    @Override
    public String getXmlTagName() {
        return "UE-associatedLogicalNG-connectionList";
    }

    @Override
    public Class<NGAP_UE_associatedLogicalNG_connectionItem> getItemType() {
        return NGAP_UE_associatedLogicalNG_connectionItem.class;
    }
}
