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

public class NGAP_PagingDRX extends NgapEnumerated {

    public static final NGAP_PagingDRX V32 = new NGAP_PagingDRX("v32");
    public static final NGAP_PagingDRX V64 = new NGAP_PagingDRX("v64");
    public static final NGAP_PagingDRX V128 = new NGAP_PagingDRX("v128");
    public static final NGAP_PagingDRX V256 = new NGAP_PagingDRX("v256");

    public NGAP_PagingDRX(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "PagingDRX";
    }

    @Override
    public String getXmlTagName() {
        return "PagingDRX";
    }
}
