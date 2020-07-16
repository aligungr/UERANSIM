package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.NGAP_AMFPointer;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.NGAP_AMFSetID;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_FiveG_TMSI;

public class NGAP_FiveG_S_TMSI extends NgapSequence {

    public NGAP_AMFSetID aMFSetID;
    public NGAP_AMFPointer aMFPointer;
    public NGAP_FiveG_TMSI fiveG_TMSI;

    @Override
    protected String getAsnName() {
        return "FiveG-S-TMSI";
    }

    @Override
    protected String getXmlTagName() {
        return "FiveG-S-TMSI";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"aMFSetID", "aMFPointer", "fiveG-TMSI"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"aMFSetID", "aMFPointer", "fiveG_TMSI"};
    }
}
