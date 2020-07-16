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

public class NGAP_DL_NGU_TNLInformationReused extends NgapEnumerated {

    public static final NGAP_DL_NGU_TNLInformationReused TRUE = new NGAP_DL_NGU_TNLInformationReused("true");

    protected NGAP_DL_NGU_TNLInformationReused(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "DL-NGU-TNLInformationReused";
    }

    @Override
    protected String getXmlTagName() {
        return "DL-NGU-TNLInformationReused";
    }
}
