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

public class NGAP_TraceDepth extends NGAP_Enumerated {

    public static final NGAP_TraceDepth MINIMUM = new NGAP_TraceDepth("minimum");
    public static final NGAP_TraceDepth MEDIUM = new NGAP_TraceDepth("medium");
    public static final NGAP_TraceDepth MAXIMUM = new NGAP_TraceDepth("maximum");
    public static final NGAP_TraceDepth MINIMUMWITHOUTVENDORSPECIFICEXTENSION = new NGAP_TraceDepth("minimumWithoutVendorSpecificExtension");
    public static final NGAP_TraceDepth MEDIUMWITHOUTVENDORSPECIFICEXTENSION = new NGAP_TraceDepth("mediumWithoutVendorSpecificExtension");
    public static final NGAP_TraceDepth MAXIMUMWITHOUTVENDORSPECIFICEXTENSION = new NGAP_TraceDepth("maximumWithoutVendorSpecificExtension");

    protected NGAP_TraceDepth(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "TraceDepth";
    }

    @Override
    public String getXmlTagName() {
        return "TraceDepth";
    }
}
