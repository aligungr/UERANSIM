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

public class NGAP_Cause extends NGAP_Choice {

    public NGAP_CauseRadioNetwork radioNetwork;
    public NGAP_CauseTransport transport;
    public NGAP_CauseNas nas;
    public NGAP_CauseProtocol protocol;
    public NGAP_CauseMisc misc;

    @Override
    public String getAsnName() {
        return "Cause";
    }

    @Override
    public String getXmlTagName() {
        return "Cause";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"radioNetwork", "transport", "nas", "protocol", "misc"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"radioNetwork", "transport", "nas", "protocol", "misc"};
    }
}
