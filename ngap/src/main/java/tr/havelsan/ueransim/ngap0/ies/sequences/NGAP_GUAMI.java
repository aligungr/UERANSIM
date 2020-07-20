package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;

public class NGAP_GUAMI extends NGAP_Sequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_AMFRegionID aMFRegionID;
    public NGAP_AMFSetID aMFSetID;
    public NGAP_AMFPointer aMFPointer;

    @Override
    public String getAsnName() {
        return "GUAMI";
    }

    @Override
    public String getXmlTagName() {
        return "GUAMI";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "aMFRegionID", "aMFSetID", "aMFPointer"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "aMFRegionID", "aMFSetID", "aMFPointer"};
    }
}
