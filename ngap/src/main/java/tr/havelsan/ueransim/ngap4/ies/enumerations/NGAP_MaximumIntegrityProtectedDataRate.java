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

public class NGAP_MaximumIntegrityProtectedDataRate extends NgapEnumerated {

    public static final NGAP_MaximumIntegrityProtectedDataRate BITRATE64KBS = new NGAP_MaximumIntegrityProtectedDataRate("bitrate64kbs");
    public static final NGAP_MaximumIntegrityProtectedDataRate MAXIMUM_UE_RATE = new NGAP_MaximumIntegrityProtectedDataRate("maximum-UE-rate");

    protected NGAP_MaximumIntegrityProtectedDataRate(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "MaximumIntegrityProtectedDataRate";
    }

    @Override
    protected String getXmlTagName() {
        return "MaximumIntegrityProtectedDataRate";
    }
}
