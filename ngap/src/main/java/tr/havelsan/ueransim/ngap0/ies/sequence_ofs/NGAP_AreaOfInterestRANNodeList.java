package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_AreaOfInterestRANNodeList extends NGAP_SequenceOf<NGAP_AreaOfInterestRANNodeItem> {

    public NGAP_AreaOfInterestRANNodeList() {
        super();
    }

    public NGAP_AreaOfInterestRANNodeList(List<NGAP_AreaOfInterestRANNodeItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "AreaOfInterestRANNodeList";
    }

    @Override
    public String getXmlTagName() {
        return "AreaOfInterestRANNodeList";
    }

    @Override
    public Class<NGAP_AreaOfInterestRANNodeItem> getItemType() {
        return NGAP_AreaOfInterestRANNodeItem.class;
    }
}
