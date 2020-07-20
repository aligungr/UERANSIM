package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_TAIBroadcastNR extends NGAP_SequenceOf<NGAP_TAIBroadcastNR_Item> {

    public NGAP_TAIBroadcastNR() {
        super();
    }

    public NGAP_TAIBroadcastNR(List<NGAP_TAIBroadcastNR_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "TAIBroadcastNR";
    }

    @Override
    public String getXmlTagName() {
        return "TAIBroadcastNR";
    }

    @Override
    public Class<NGAP_TAIBroadcastNR_Item> getItemType() {
        return NGAP_TAIBroadcastNR_Item.class;
    }
}
