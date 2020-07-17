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

public class NGAP_CauseNas extends NgapEnumerated {

    public static final NGAP_CauseNas NORMAL_RELEASE = new NGAP_CauseNas("normal-release");
    public static final NGAP_CauseNas AUTHENTICATION_FAILURE = new NGAP_CauseNas("authentication-failure");
    public static final NGAP_CauseNas DEREGISTER = new NGAP_CauseNas("deregister");
    public static final NGAP_CauseNas UNSPECIFIED = new NGAP_CauseNas("unspecified");

    public NGAP_CauseNas(String sValue) {
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
