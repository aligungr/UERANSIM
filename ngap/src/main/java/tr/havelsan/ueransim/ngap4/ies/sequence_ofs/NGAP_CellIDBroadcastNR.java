package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_CellIDBroadcastNR_Item;

public class NGAP_CellIDBroadcastNR extends NgapSequenceOf<NGAP_CellIDBroadcastNR_Item> {

    @Override
    protected String getAsnName() {
        return "CellIDBroadcastNR";
    }

    @Override
    protected String getXmlTagName() {
        return "CellIDBroadcastNR";
    }

    @Override
    public Class<NGAP_CellIDBroadcastNR_Item> getItemType() {
        return NGAP_CellIDBroadcastNR_Item.class;
    }
}
