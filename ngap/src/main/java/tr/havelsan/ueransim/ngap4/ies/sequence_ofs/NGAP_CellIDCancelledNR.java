package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_CellIDCancelledNR_Item;

public class NGAP_CellIDCancelledNR extends NgapSequenceOf<NGAP_CellIDCancelledNR_Item> {

    @Override
    protected String getAsnName() {
        return "CellIDCancelledNR";
    }

    @Override
    protected String getXmlTagName() {
        return "CellIDCancelledNR";
    }

    @Override
    public Class<NGAP_CellIDCancelledNR_Item> getItemType() {
        return NGAP_CellIDCancelledNR_Item.class;
    }
}
