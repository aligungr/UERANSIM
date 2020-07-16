package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.NgapChoice;
import tr.havelsan.ueransim.ngap4.ies.enumerations.*;

public class NGAP_Cause extends NgapChoice {

    public NGAP_CauseRadioNetwork radioNetwork;
    public NGAP_CauseTransport transport;
    public NGAP_CauseNas nas;
    public NGAP_CauseProtocol protocol;
    public NGAP_CauseMisc misc;

    @Override
    protected String getAsnName() {
        return "Cause";
    }

    @Override
    protected String getXmlTagName() {
        return "Cause";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"radioNetwork", "transport", "nas", "protocol", "misc"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"radioNetwork", "transport", "nas", "protocol", "misc"};
    }
}
