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

public class NGAP_CauseMisc extends NgapEnumerated {

    public static final NGAP_CauseMisc CONTROL_PROCESSING_OVERLOAD = new NGAP_CauseMisc("control-processing-overload");
    public static final NGAP_CauseMisc NOT_ENOUGH_USER_PLANE_PROCESSING_RESOURCES = new NGAP_CauseMisc("not-enough-user-plane-processing-resources");
    public static final NGAP_CauseMisc HARDWARE_FAILURE = new NGAP_CauseMisc("hardware-failure");
    public static final NGAP_CauseMisc OM_INTERVENTION = new NGAP_CauseMisc("om-intervention");
    public static final NGAP_CauseMisc UNKNOWN_PLMN = new NGAP_CauseMisc("unknown-PLMN");
    public static final NGAP_CauseMisc UNSPECIFIED = new NGAP_CauseMisc("unspecified");

    protected NGAP_CauseMisc(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "CauseMisc";
    }

    @Override
    protected String getXmlTagName() {
        return "CauseMisc";
    }
}
