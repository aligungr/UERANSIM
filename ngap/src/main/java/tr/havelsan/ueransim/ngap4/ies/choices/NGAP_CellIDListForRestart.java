package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.NgapChoice;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_EUTRA_CGIList;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_NR_CGIList;

public class NGAP_CellIDListForRestart extends NgapChoice {

    public NGAP_EUTRA_CGIList eUTRA_CGIListforRestart;
    public NGAP_NR_CGIList nR_CGIListforRestart;

    @Override
    protected String getAsnName() {
        return "CellIDListForRestart";
    }

    @Override
    protected String getXmlTagName() {
        return "CellIDListForRestart";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"eUTRA-CGIListforRestart", "nR-CGIListforRestart"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"eUTRA_CGIListforRestart", "nR_CGIListforRestart"};
    }
}
