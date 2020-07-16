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

public class NGAP_SONInformation extends NgapChoice {

    public NGAP_SONInformationRequest sONInformationRequest;
    public NGAP_SONInformationReply sONInformationReply;

    @Override
    protected String getAsnName() {
        return "SONInformation";
    }

    @Override
    protected String getXmlTagName() {
        return "SONInformation";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"sONInformationRequest", "sONInformationReply"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"sONInformationRequest", "sONInformationReply"};
    }
}
