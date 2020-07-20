package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_TNLInformationList extends NGAP_SequenceOf<NGAP_TNLInformationItem> {

    public NGAP_TNLInformationList() {
        super();
    }

    public NGAP_TNLInformationList(List<NGAP_TNLInformationItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "TNLInformationList";
    }

    @Override
    public String getXmlTagName() {
        return "TNLInformationList";
    }

    @Override
    public Class<NGAP_TNLInformationItem> getItemType() {
        return NGAP_TNLInformationItem.class;
    }
}
