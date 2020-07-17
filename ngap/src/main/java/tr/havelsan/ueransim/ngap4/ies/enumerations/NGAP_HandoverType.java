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

public class NGAP_HandoverType extends NgapEnumerated {

    public static final NGAP_HandoverType INTRA5GS = new NGAP_HandoverType("intra5gs");
    public static final NGAP_HandoverType FIVEGS_TO_EPS = new NGAP_HandoverType("fivegs-to-eps");
    public static final NGAP_HandoverType EPS_TO_5GS = new NGAP_HandoverType("eps-to-5gs");

    public NGAP_HandoverType(String sValue) {
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
