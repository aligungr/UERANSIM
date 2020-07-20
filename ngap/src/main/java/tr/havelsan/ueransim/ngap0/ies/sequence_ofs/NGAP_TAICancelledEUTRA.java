package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_TAICancelledEUTRA extends NGAP_SequenceOf<NGAP_TAICancelledEUTRA_Item> {

    public NGAP_TAICancelledEUTRA() {
        super();
    }

    public NGAP_TAICancelledEUTRA(List<NGAP_TAICancelledEUTRA_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "TAICancelledEUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "TAICancelledEUTRA";
    }

    @Override
    public Class<NGAP_TAICancelledEUTRA_Item> getItemType() {
        return NGAP_TAICancelledEUTRA_Item.class;
    }
}
