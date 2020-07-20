package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_SliceSupportList extends NGAP_SequenceOf<NGAP_SliceSupportItem> {

    public NGAP_SliceSupportList() {
        super();
    }

    public NGAP_SliceSupportList(List<NGAP_SliceSupportItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "SliceSupportList";
    }

    @Override
    public String getXmlTagName() {
        return "SliceSupportList";
    }

    @Override
    public Class<NGAP_SliceSupportItem> getItemType() {
        return NGAP_SliceSupportItem.class;
    }
}
