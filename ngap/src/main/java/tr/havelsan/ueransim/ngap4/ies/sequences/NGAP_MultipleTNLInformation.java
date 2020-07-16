package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_TNLInformationList;

public class NGAP_MultipleTNLInformation extends NgapSequence {

    public NGAP_TNLInformationList tNLInformationList;

    @Override
    protected String getAsnName() {
        return "MultipleTNLInformation";
    }

    @Override
    protected String getXmlTagName() {
        return "MultipleTNLInformation";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"tNLInformationList"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"tNLInformationList"};
    }
}
