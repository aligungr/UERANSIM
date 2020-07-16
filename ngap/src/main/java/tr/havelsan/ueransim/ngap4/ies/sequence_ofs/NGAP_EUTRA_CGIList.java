package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_EUTRA_CGI;

public class NGAP_EUTRA_CGIList extends NgapSequenceOf<NGAP_EUTRA_CGI> {

    @Override
    protected String getAsnName() {
        return "EUTRA-CGIList";
    }

    @Override
    protected String getXmlTagName() {
        return "EUTRA-CGIList";
    }

    @Override
    public Class<NGAP_EUTRA_CGI> getItemType() {
        return NGAP_EUTRA_CGI.class;
    }
}
