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

public class NGAP_HandoverType extends NGAP_Enumerated {

    public static final NGAP_HandoverType INTRA5GS = new NGAP_HandoverType("intra5gs");
    public static final NGAP_HandoverType FIVEGS_TO_EPS = new NGAP_HandoverType("fivegs-to-eps");
    public static final NGAP_HandoverType EPS_TO_5GS = new NGAP_HandoverType("eps-to-5gs");

    protected NGAP_HandoverType(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "HandoverType";
    }

    @Override
    public String getXmlTagName() {
        return "HandoverType";
    }
}
