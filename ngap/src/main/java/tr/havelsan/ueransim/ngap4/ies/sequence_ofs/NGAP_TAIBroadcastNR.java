package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_TAIBroadcastNR_Item;

public class NGAP_TAIBroadcastNR extends NgapSequenceOf<NGAP_TAIBroadcastNR_Item> {

    @Override
    protected String getAsnName() {
        return "TAIBroadcastNR";
    }

    @Override
    protected String getXmlTagName() {
        return "TAIBroadcastNR";
    }

    @Override
    public Class<NGAP_TAIBroadcastNR_Item> getItemType() {
        return NGAP_TAIBroadcastNR_Item.class;
    }
}
