package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_TAIBroadcastEUTRA_Item;

public class NGAP_TAIBroadcastEUTRA extends NgapSequenceOf<NGAP_TAIBroadcastEUTRA_Item> {

    @Override
    protected String getAsnName() {
        return "TAIBroadcastEUTRA";
    }

    @Override
    protected String getXmlTagName() {
        return "TAIBroadcastEUTRA";
    }

    @Override
    public Class<NGAP_TAIBroadcastEUTRA_Item> getItemType() {
        return NGAP_TAIBroadcastEUTRA_Item.class;
    }
}
