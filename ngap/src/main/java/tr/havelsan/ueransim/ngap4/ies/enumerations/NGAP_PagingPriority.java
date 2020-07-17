package tr.havelsan.ueransim.ngap4.ies.enumerations;

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

public class NGAP_PagingPriority extends NgapEnumerated {

    public static final NGAP_PagingPriority PRIOLEVEL1 = new NGAP_PagingPriority("priolevel1");
    public static final NGAP_PagingPriority PRIOLEVEL2 = new NGAP_PagingPriority("priolevel2");
    public static final NGAP_PagingPriority PRIOLEVEL3 = new NGAP_PagingPriority("priolevel3");
    public static final NGAP_PagingPriority PRIOLEVEL4 = new NGAP_PagingPriority("priolevel4");
    public static final NGAP_PagingPriority PRIOLEVEL5 = new NGAP_PagingPriority("priolevel5");
    public static final NGAP_PagingPriority PRIOLEVEL6 = new NGAP_PagingPriority("priolevel6");
    public static final NGAP_PagingPriority PRIOLEVEL7 = new NGAP_PagingPriority("priolevel7");
    public static final NGAP_PagingPriority PRIOLEVEL8 = new NGAP_PagingPriority("priolevel8");

    public NGAP_PagingPriority(String sValue) {
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
