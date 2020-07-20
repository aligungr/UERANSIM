package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_AreaOfInterestList extends NGAP_SequenceOf<NGAP_AreaOfInterestItem> {

    public NGAP_AreaOfInterestList() {
        super();
    }

    public NGAP_AreaOfInterestList(List<NGAP_AreaOfInterestItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "AreaOfInterestList";
    }

    @Override
    public String getXmlTagName() {
        return "AreaOfInterestList";
    }

    @Override
    public Class<NGAP_AreaOfInterestItem> getItemType() {
        return NGAP_AreaOfInterestItem.class;
    }
}
