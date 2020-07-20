package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_TAIListForWarning extends NGAP_SequenceOf<NGAP_TAI> {

    public NGAP_TAIListForWarning() {
        super();
    }

    public NGAP_TAIListForWarning(List<NGAP_TAI> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "TAIListForWarning";
    }

    @Override
    public String getXmlTagName() {
        return "TAIListForWarning";
    }

    @Override
    public Class<NGAP_TAI> getItemType() {
        return NGAP_TAI.class;
    }
}
