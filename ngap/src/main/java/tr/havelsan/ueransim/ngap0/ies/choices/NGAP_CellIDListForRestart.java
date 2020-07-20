package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;

public class NGAP_CellIDListForRestart extends NGAP_Choice {

    public NGAP_EUTRA_CGIList eUTRA_CGIListforRestart;
    public NGAP_NR_CGIList nR_CGIListforRestart;

    @Override
    public String getAsnName() {
        return "CellIDListForRestart";
    }

    @Override
    public String getXmlTagName() {
        return "CellIDListForRestart";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"eUTRA-CGIListforRestart", "nR-CGIListforRestart"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"eUTRA_CGIListforRestart", "nR_CGIListforRestart"};
    }
}
