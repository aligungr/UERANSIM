package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_CellIDBroadcastEUTRA_Item;

public class NGAP_CellIDBroadcastEUTRA extends NgapSequenceOf<NGAP_CellIDBroadcastEUTRA_Item> {

    @Override
    protected String getAsnName() {
        return "CellIDBroadcastEUTRA";
    }

    @Override
    protected String getXmlTagName() {
        return "CellIDBroadcastEUTRA";
    }

    @Override
    public Class<NGAP_CellIDBroadcastEUTRA_Item> getItemType() {
        return NGAP_CellIDBroadcastEUTRA_Item.class;
    }
}
