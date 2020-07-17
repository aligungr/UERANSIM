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

public class NGAP_TNLAssociationUsage extends NGAP_Enumerated {

    public static final NGAP_TNLAssociationUsage UE = new NGAP_TNLAssociationUsage("ue");
    public static final NGAP_TNLAssociationUsage NON_UE = new NGAP_TNLAssociationUsage("non-ue");
    public static final NGAP_TNLAssociationUsage BOTH = new NGAP_TNLAssociationUsage("both");

    protected NGAP_TNLAssociationUsage(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "TNLAssociationUsage";
    }

    @Override
    public String getXmlTagName() {
        return "TNLAssociationUsage";
    }
}
