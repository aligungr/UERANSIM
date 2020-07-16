package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_EUTRA_CGI;

public class NGAP_EUTRA_CGIListForWarning extends NgapSequenceOf<NGAP_EUTRA_CGI> {

    @Override
    protected String getAsnName() {
        return "EUTRA-CGIListForWarning";
    }

    @Override
    protected String getXmlTagName() {
        return "EUTRA-CGIListForWarning";
    }

    @Override
    public Class<NGAP_EUTRA_CGI> getItemType() {
        return NGAP_EUTRA_CGI.class;
    }
}
