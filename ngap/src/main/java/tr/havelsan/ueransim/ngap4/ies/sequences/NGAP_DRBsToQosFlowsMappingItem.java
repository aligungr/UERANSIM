package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.ngap4.pdu.*;
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

public class NGAP_DRBsToQosFlowsMappingItem extends NGAP_Sequence {

    public NGAP_DRB_ID dRB_ID;
    public NGAP_AssociatedQosFlowList associatedQosFlowList;

    @Override
    public String getAsnName() {
        return "DRBsToQosFlowsMappingItem";
    }

    @Override
    public String getXmlTagName() {
        return "DRBsToQosFlowsMappingItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"dRB-ID", "associatedQosFlowList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"dRB_ID", "associatedQosFlowList"};
    }
}
