package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_ForbiddenAreaInformation_Item;

public class NGAP_ForbiddenAreaInformation extends NgapSequenceOf<NGAP_ForbiddenAreaInformation_Item> {

    @Override
    protected String getAsnName() {
        return "ForbiddenAreaInformation";
    }

    @Override
    protected String getXmlTagName() {
        return "ForbiddenAreaInformation";
    }

    @Override
    public Class<NGAP_ForbiddenAreaInformation_Item> getItemType() {
        return NGAP_ForbiddenAreaInformation_Item.class;
    }
}
