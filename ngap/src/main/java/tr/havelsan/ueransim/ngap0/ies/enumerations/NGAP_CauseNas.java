/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.ngap0.ies.enumerations;

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

public class NGAP_CauseNas extends NGAP_Enumerated {

    public static final NGAP_CauseNas NORMAL_RELEASE = new NGAP_CauseNas("normal-release");
    public static final NGAP_CauseNas AUTHENTICATION_FAILURE = new NGAP_CauseNas("authentication-failure");
    public static final NGAP_CauseNas DEREGISTER = new NGAP_CauseNas("deregister");
    public static final NGAP_CauseNas UNSPECIFIED = new NGAP_CauseNas("unspecified");

    protected NGAP_CauseNas(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "CauseNas";
    }

    @Override
    public String getXmlTagName() {
        return "CauseNas";
    }
}
