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

public class NGAP_DL_NGU_TNLInformationReused extends NGAP_Enumerated {

    public static final NGAP_DL_NGU_TNLInformationReused TRUE = new NGAP_DL_NGU_TNLInformationReused("true");

    protected NGAP_DL_NGU_TNLInformationReused(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "DL-NGU-TNLInformationReused";
    }

    @Override
    public String getXmlTagName() {
        return "DL-NGU-TNLInformationReused";
    }
}
