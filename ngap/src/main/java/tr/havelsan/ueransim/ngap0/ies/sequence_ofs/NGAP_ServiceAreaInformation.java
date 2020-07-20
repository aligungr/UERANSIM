package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_ServiceAreaInformation extends NGAP_SequenceOf<NGAP_ServiceAreaInformation_Item> {

    public NGAP_ServiceAreaInformation() {
        super();
    }

    public NGAP_ServiceAreaInformation(List<NGAP_ServiceAreaInformation_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "ServiceAreaInformation";
    }

    @Override
    public String getXmlTagName() {
        return "ServiceAreaInformation";
    }

    @Override
    public Class<NGAP_ServiceAreaInformation_Item> getItemType() {
        return NGAP_ServiceAreaInformation_Item.class;
    }
}
