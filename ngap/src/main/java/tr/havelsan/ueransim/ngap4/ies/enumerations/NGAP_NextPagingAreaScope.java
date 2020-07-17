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

public class NGAP_NextPagingAreaScope extends NgapEnumerated {

    public static final NGAP_NextPagingAreaScope SAME = new NGAP_NextPagingAreaScope("same");
    public static final NGAP_NextPagingAreaScope CHANGED = new NGAP_NextPagingAreaScope("changed");

    public NGAP_NextPagingAreaScope(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "NextPagingAreaScope";
    }

    @Override
    public String getXmlTagName() {
        return "NextPagingAreaScope";
    }
}
