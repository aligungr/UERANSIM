package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.NGAP_AMFPointer;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.NGAP_AMFRegionID;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.NGAP_AMFSetID;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_PLMNIdentity;

public class NGAP_GUAMI extends NgapSequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_AMFRegionID aMFRegionID;
    public NGAP_AMFSetID aMFSetID;
    public NGAP_AMFPointer aMFPointer;

    @Override
    protected String getAsnName() {
        return "GUAMI";
    }

    @Override
    protected String getXmlTagName() {
        return "GUAMI";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "aMFRegionID", "aMFSetID", "aMFPointer"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "aMFRegionID", "aMFSetID", "aMFPointer"};
    }
}
