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

public class NGAP_GlobalRANNodeID extends NgapChoice {

    public NGAP_GlobalGNB_ID globalGNB_ID;
    public NGAP_GlobalNgENB_ID globalNgENB_ID;
    public NGAP_GlobalN3IWF_ID globalN3IWF_ID;

    @Override
    protected String getAsnName() {
        return "GlobalRANNodeID";
    }

    @Override
    protected String getXmlTagName() {
        return "GlobalRANNodeID";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"globalGNB-ID", "globalNgENB-ID", "globalN3IWF-ID"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"globalGNB_ID", "globalNgENB_ID", "globalN3IWF_ID"};
    }
}
