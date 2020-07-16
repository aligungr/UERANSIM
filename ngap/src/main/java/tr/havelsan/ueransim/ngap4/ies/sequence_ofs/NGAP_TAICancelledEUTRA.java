package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_TAICancelledEUTRA_Item;

public class NGAP_TAICancelledEUTRA extends NgapSequenceOf<NGAP_TAICancelledEUTRA_Item> {

    @Override
    protected String getAsnName() {
        return "TAICancelledEUTRA";
    }

    @Override
    protected String getXmlTagName() {
        return "TAICancelledEUTRA";
    }

    @Override
    public Class<NGAP_TAICancelledEUTRA_Item> getItemType() {
        return NGAP_TAICancelledEUTRA_Item.class;
    }
}
