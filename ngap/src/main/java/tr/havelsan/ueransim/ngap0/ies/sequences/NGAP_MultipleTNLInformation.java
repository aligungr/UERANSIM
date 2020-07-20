package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;

public class NGAP_MultipleTNLInformation extends NGAP_Sequence {

    public NGAP_TNLInformationList tNLInformationList;

    @Override
    public String getAsnName() {
        return "MultipleTNLInformation";
    }

    @Override
    public String getXmlTagName() {
        return "MultipleTNLInformation";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"tNLInformationList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"tNLInformationList"};
    }
}
