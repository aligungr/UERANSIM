package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

public class NGAP_UP_TNLInformation extends NGAP_Choice {

    public NGAP_SingleTNLInformation singleTNLInformation;
    public NGAP_MultipleTNLInformation multipleTNLInformation;

    @Override
    public String getAsnName() {
        return "UP-TNLInformation";
    }

    @Override
    public String getXmlTagName() {
        return "UP-TNLInformation";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"singleTNLInformation", "multipleTNLInformation"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"singleTNLInformation", "multipleTNLInformation"};
    }
}
