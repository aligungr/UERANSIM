package tr.havelsan.ueransim.ngap4.ies.sequences;

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

public class NGAP_PLMNSupportItem extends NgapSequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_SliceSupportList sliceSupportList;

    @Override
    protected String getAsnName() {
        return "PLMNSupportItem";
    }

    @Override
    protected String getXmlTagName() {
        return "PLMNSupportItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "sliceSupportList"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "sliceSupportList"};
    }
}
