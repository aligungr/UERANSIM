package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_TAICancelledNR_Item;

public class NGAP_TAICancelledNR extends NgapSequenceOf<NGAP_TAICancelledNR_Item> {

    @Override
    protected String getAsnName() {
        return "TAICancelledNR";
    }

    @Override
    protected String getXmlTagName() {
        return "TAICancelledNR";
    }

    @Override
    public Class<NGAP_TAICancelledNR_Item> getItemType() {
        return NGAP_TAICancelledNR_Item.class;
    }
}
