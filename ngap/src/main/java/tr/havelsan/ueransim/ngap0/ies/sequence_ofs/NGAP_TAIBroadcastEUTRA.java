package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_TAIBroadcastEUTRA extends NGAP_SequenceOf<NGAP_TAIBroadcastEUTRA_Item> {

    public NGAP_TAIBroadcastEUTRA() {
        super();
    }

    public NGAP_TAIBroadcastEUTRA(List<NGAP_TAIBroadcastEUTRA_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "TAIBroadcastEUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "TAIBroadcastEUTRA";
    }

    @Override
    public Class<NGAP_TAIBroadcastEUTRA_Item> getItemType() {
        return NGAP_TAIBroadcastEUTRA_Item.class;
    }
}
