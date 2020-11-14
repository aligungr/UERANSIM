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

public class NGAP_UE_NGAP_IDs extends NGAP_Choice {

    public NGAP_UE_NGAP_ID_pair uE_NGAP_ID_pair;
    public NGAP_AMF_UE_NGAP_ID aMF_UE_NGAP_ID;

    @Override
    public String getAsnName() {
        return "UE-NGAP-IDs";
    }

    @Override
    public String getXmlTagName() {
        return "UE-NGAP-IDs";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"uE-NGAP-ID-pair", "aMF-UE-NGAP-ID"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"uE_NGAP_ID_pair", "aMF_UE_NGAP_ID"};
    }
}
