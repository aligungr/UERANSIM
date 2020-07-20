package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_ForbiddenAreaInformation extends NGAP_SequenceOf<NGAP_ForbiddenAreaInformation_Item> {

    public NGAP_ForbiddenAreaInformation() {
        super();
    }

    public NGAP_ForbiddenAreaInformation(List<NGAP_ForbiddenAreaInformation_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "ForbiddenAreaInformation";
    }

    @Override
    public String getXmlTagName() {
        return "ForbiddenAreaInformation";
    }

    @Override
    public Class<NGAP_ForbiddenAreaInformation_Item> getItemType() {
        return NGAP_ForbiddenAreaInformation_Item.class;
    }
}
