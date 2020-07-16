package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_AllowedNSSAI_Item;

public class NGAP_AllowedNSSAI extends NgapSequenceOf<NGAP_AllowedNSSAI_Item> {

    @Override
    protected String getAsnName() {
        return "AllowedNSSAI";
    }

    @Override
    protected String getXmlTagName() {
        return "AllowedNSSAI";
    }

    @Override
    public Class<NGAP_AllowedNSSAI_Item> getItemType() {
        return NGAP_AllowedNSSAI_Item.class;
    }
}
