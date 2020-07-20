package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_SliceOverloadList extends NGAP_SequenceOf<NGAP_SliceOverloadItem> {

    public NGAP_SliceOverloadList() {
        super();
    }

    public NGAP_SliceOverloadList(List<NGAP_SliceOverloadItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "SliceOverloadList";
    }

    @Override
    public String getXmlTagName() {
        return "SliceOverloadList";
    }

    @Override
    public Class<NGAP_SliceOverloadItem> getItemType() {
        return NGAP_SliceOverloadItem.class;
    }
}
