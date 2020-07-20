package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;

public class NGAP_FiveG_S_TMSI extends NGAP_Sequence {

    public NGAP_AMFSetID aMFSetID;
    public NGAP_AMFPointer aMFPointer;
    public NGAP_FiveG_TMSI fiveG_TMSI;

    @Override
    public String getAsnName() {
        return "FiveG-S-TMSI";
    }

    @Override
    public String getXmlTagName() {
        return "FiveG-S-TMSI";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"aMFSetID", "aMFPointer", "fiveG-TMSI"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"aMFSetID", "aMFPointer", "fiveG_TMSI"};
    }
}
