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

public class NGAP_ConfidentialityProtectionIndication extends NGAP_Enumerated {

    public static final NGAP_ConfidentialityProtectionIndication REQUIRED = new NGAP_ConfidentialityProtectionIndication("required");
    public static final NGAP_ConfidentialityProtectionIndication PREFERRED = new NGAP_ConfidentialityProtectionIndication("preferred");
    public static final NGAP_ConfidentialityProtectionIndication NOT_NEEDED = new NGAP_ConfidentialityProtectionIndication("not-needed");

    protected NGAP_ConfidentialityProtectionIndication(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "ConfidentialityProtectionIndication";
    }

    @Override
    public String getXmlTagName() {
        return "ConfidentialityProtectionIndication";
    }
}
