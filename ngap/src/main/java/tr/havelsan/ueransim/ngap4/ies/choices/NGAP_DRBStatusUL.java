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

public class NGAP_DRBStatusUL extends NgapChoice {

    public NGAP_DRBStatusUL12 dRBStatusUL12;
    public NGAP_DRBStatusUL18 dRBStatusUL18;

    @Override
    protected String getAsnName() {
        return "DRBStatusUL";
    }

    @Override
    protected String getXmlTagName() {
        return "DRBStatusUL";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"dRBStatusUL12", "dRBStatusUL18"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"dRBStatusUL12", "dRBStatusUL18"};
    }
}
