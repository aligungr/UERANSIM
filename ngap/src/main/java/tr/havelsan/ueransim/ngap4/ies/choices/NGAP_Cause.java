package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap4.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap4.ies.sequences.*;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap4.ies.choices.*;
import tr.havelsan.ueransim.ngap4.ies.integers.*;
import tr.havelsan.ueransim.ngap4.ies.enumerations.*;

import java.util.List;

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
