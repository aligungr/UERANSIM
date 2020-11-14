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

public class NGAP_PagingPriority extends NGAP_Enumerated {

    public static final NGAP_PagingPriority PRIOLEVEL1 = new NGAP_PagingPriority("priolevel1");
    public static final NGAP_PagingPriority PRIOLEVEL2 = new NGAP_PagingPriority("priolevel2");
    public static final NGAP_PagingPriority PRIOLEVEL3 = new NGAP_PagingPriority("priolevel3");
    public static final NGAP_PagingPriority PRIOLEVEL4 = new NGAP_PagingPriority("priolevel4");
    public static final NGAP_PagingPriority PRIOLEVEL5 = new NGAP_PagingPriority("priolevel5");
    public static final NGAP_PagingPriority PRIOLEVEL6 = new NGAP_PagingPriority("priolevel6");
    public static final NGAP_PagingPriority PRIOLEVEL7 = new NGAP_PagingPriority("priolevel7");
    public static final NGAP_PagingPriority PRIOLEVEL8 = new NGAP_PagingPriority("priolevel8");

    protected NGAP_PagingPriority(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "PagingPriority";
    }

    @Override
    public String getXmlTagName() {
        return "PagingPriority";
    }
}
