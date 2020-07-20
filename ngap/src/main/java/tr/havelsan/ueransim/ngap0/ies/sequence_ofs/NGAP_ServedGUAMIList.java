package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_ServedGUAMIList extends NGAP_SequenceOf<NGAP_ServedGUAMIItem> {

    public NGAP_ServedGUAMIList() {
        super();
    }

    public NGAP_ServedGUAMIList(List<NGAP_ServedGUAMIItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "ServedGUAMIList";
    }

    @Override
    public String getXmlTagName() {
        return "ServedGUAMIList";
    }

    @Override
    public Class<NGAP_ServedGUAMIItem> getItemType() {
        return NGAP_ServedGUAMIItem.class;
    }
}
