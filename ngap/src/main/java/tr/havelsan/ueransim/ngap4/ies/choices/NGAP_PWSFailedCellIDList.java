package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.NgapChoice;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_EUTRA_CGIList;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_NR_CGIList;

public class NGAP_PWSFailedCellIDList extends NgapChoice {

    public NGAP_EUTRA_CGIList eUTRA_CGI_PWSFailedList;
    public NGAP_NR_CGIList nR_CGI_PWSFailedList;

    @Override
    protected String getAsnName() {
        return "PWSFailedCellIDList";
    }

    @Override
    protected String getXmlTagName() {
        return "PWSFailedCellIDList";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"eUTRA-CGI-PWSFailedList", "nR-CGI-PWSFailedList"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"eUTRA_CGI_PWSFailedList", "nR_CGI_PWSFailedList"};
    }
}
