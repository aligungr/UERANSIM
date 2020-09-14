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

public class NGAP_PagingDRX extends NGAP_Enumerated {

    public static final NGAP_PagingDRX V32 = new NGAP_PagingDRX("v32");
    public static final NGAP_PagingDRX V64 = new NGAP_PagingDRX("v64");
    public static final NGAP_PagingDRX V128 = new NGAP_PagingDRX("v128");
    public static final NGAP_PagingDRX V256 = new NGAP_PagingDRX("v256");

    protected NGAP_PagingDRX(String sValue) {
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
