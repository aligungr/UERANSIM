package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_EUTRA_CGIListForWarning extends NGAP_SequenceOf<NGAP_EUTRA_CGI> {

    public NGAP_EUTRA_CGIListForWarning() {
        super();
    }

    public NGAP_EUTRA_CGIListForWarning(List<NGAP_EUTRA_CGI> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "EUTRA-CGIListForWarning";
    }

    @Override
    public String getXmlTagName() {
        return "EUTRA-CGIListForWarning";
    }

    @Override
    public Class<NGAP_EUTRA_CGI> getItemType() {
        return NGAP_EUTRA_CGI.class;
    }
}
