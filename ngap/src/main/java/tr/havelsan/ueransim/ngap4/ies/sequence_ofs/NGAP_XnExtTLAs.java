package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_XnExtTLA_Item;

public class NGAP_XnExtTLAs extends NgapSequenceOf<NGAP_XnExtTLA_Item> {

    @Override
    protected String getAsnName() {
        return "XnExtTLAs";
    }

    @Override
    protected String getXmlTagName() {
        return "XnExtTLAs";
    }

    @Override
    public Class<NGAP_XnExtTLA_Item> getItemType() {
        return NGAP_XnExtTLA_Item.class;
    }
}
