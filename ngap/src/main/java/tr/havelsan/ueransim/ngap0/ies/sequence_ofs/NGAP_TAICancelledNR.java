package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_TAICancelledNR extends NGAP_SequenceOf<NGAP_TAICancelledNR_Item> {

    public NGAP_TAICancelledNR() {
        super();
    }

    public NGAP_TAICancelledNR(List<NGAP_TAICancelledNR_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "TAICancelledNR";
    }

    @Override
    public String getXmlTagName() {
        return "TAICancelledNR";
    }

    @Override
    public Class<NGAP_TAICancelledNR_Item> getItemType() {
        return NGAP_TAICancelledNR_Item.class;
    }
}
