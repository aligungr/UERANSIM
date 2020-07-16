package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_CellIDCancelledEUTRA_Item;

public class NGAP_CellIDCancelledEUTRA extends NgapSequenceOf<NGAP_CellIDCancelledEUTRA_Item> {

    @Override
    protected String getAsnName() {
        return "CellIDCancelledEUTRA";
    }

    @Override
    protected String getXmlTagName() {
        return "CellIDCancelledEUTRA";
    }

    @Override
    public Class<NGAP_CellIDCancelledEUTRA_Item> getItemType() {
        return NGAP_CellIDCancelledEUTRA_Item.class;
    }
}
