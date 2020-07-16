package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.NgapChoice;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_MultipleTNLInformation;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_SingleTNLInformation;

public class NGAP_UP_TNLInformation extends NgapChoice {

    public NGAP_SingleTNLInformation singleTNLInformation;
    public NGAP_MultipleTNLInformation multipleTNLInformation;

    @Override
    protected String getAsnName() {
        return "UP-TNLInformation";
    }

    @Override
    protected String getXmlTagName() {
        return "UP-TNLInformation";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"singleTNLInformation", "multipleTNLInformation"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"singleTNLInformation", "multipleTNLInformation"};
    }
}
