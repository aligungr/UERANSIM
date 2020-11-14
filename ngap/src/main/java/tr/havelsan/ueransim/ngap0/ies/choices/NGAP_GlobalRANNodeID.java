/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.pdu.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

import java.util.List;

public class NGAP_GlobalRANNodeID extends NGAP_Choice {

    public NGAP_GlobalGNB_ID globalGNB_ID;
    public NGAP_GlobalNgENB_ID globalNgENB_ID;
    public NGAP_GlobalN3IWF_ID globalN3IWF_ID;

    @Override
    public String getAsnName() {
        return "GlobalRANNodeID";
    }

    @Override
    public String getXmlTagName() {
        return "GlobalRANNodeID";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"globalGNB-ID", "globalNgENB-ID", "globalN3IWF-ID"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"globalGNB_ID", "globalNgENB_ID", "globalN3IWF_ID"};
    }
}
