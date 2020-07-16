package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_NR_CGI;

public class NGAP_NR_CGIListForWarning extends NgapSequenceOf<NGAP_NR_CGI> {

    @Override
    protected String getAsnName() {
        return "NR-CGIListForWarning";
    }

    @Override
    protected String getXmlTagName() {
        return "NR-CGIListForWarning";
    }

    @Override
    public Class<NGAP_NR_CGI> getItemType() {
        return NGAP_NR_CGI.class;
    }
}
