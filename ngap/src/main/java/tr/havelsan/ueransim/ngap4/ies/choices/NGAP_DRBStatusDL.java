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

public class NGAP_DRBStatusDL extends NgapChoice {

    public NGAP_DRBStatusDL12 dRBStatusDL12;
    public NGAP_DRBStatusDL18 dRBStatusDL18;

    @Override
    protected String getAsnName() {
        return "DRBStatusDL";
    }

    @Override
    protected String getXmlTagName() {
        return "DRBStatusDL";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"dRBStatusDL12", "dRBStatusDL18"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"dRBStatusDL12", "dRBStatusDL18"};
    }
}
