package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_ServiceAreaInformation_Item;

public class NGAP_ServiceAreaInformation extends NgapSequenceOf<NGAP_ServiceAreaInformation_Item> {

    @Override
    protected String getAsnName() {
        return "ServiceAreaInformation";
    }

    @Override
    protected String getXmlTagName() {
        return "ServiceAreaInformation";
    }

    @Override
    public Class<NGAP_ServiceAreaInformation_Item> getItemType() {
        return NGAP_ServiceAreaInformation_Item.class;
    }
}
