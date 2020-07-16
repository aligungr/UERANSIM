package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_RATRestrictions_Item;

public class NGAP_RATRestrictions extends NgapSequenceOf<NGAP_RATRestrictions_Item> {

    @Override
    protected String getAsnName() {
        return "RATRestrictions";
    }

    @Override
    protected String getXmlTagName() {
        return "RATRestrictions";
    }

    @Override
    public Class<NGAP_RATRestrictions_Item> getItemType() {
        return NGAP_RATRestrictions_Item.class;
    }
}
