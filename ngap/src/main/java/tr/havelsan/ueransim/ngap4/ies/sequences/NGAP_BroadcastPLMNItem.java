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

import java.util.List;

public class NGAP_BroadcastPLMNItem extends NgapSequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_SliceSupportList tAISliceSupportList;

    @Override
    protected String getAsnName() {
        return "BroadcastPLMNItem";
    }

    @Override
    protected String getXmlTagName() {
        return "BroadcastPLMNItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "tAISliceSupportList"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "tAISliceSupportList"};
    }
}
