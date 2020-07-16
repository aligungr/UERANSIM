package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_TAI;

public class NGAP_TAIListForWarning extends NgapSequenceOf<NGAP_TAI> {

    @Override
    protected String getAsnName() {
        return "TAIListForWarning";
    }

    @Override
    protected String getXmlTagName() {
        return "TAIListForWarning";
    }

    @Override
    public Class<NGAP_TAI> getItemType() {
        return NGAP_TAI.class;
    }
}
