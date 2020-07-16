package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

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

public class NGAP_QosFlowAddOrModifyRequestList extends NgapSequenceOf<NGAP_QosFlowAddOrModifyRequestItem> {

    @Override
    protected String getAsnName() {
        return "QosFlowAddOrModifyRequestList";
    }

    @Override
    protected String getXmlTagName() {
        return "QosFlowAddOrModifyRequestList";
    }

    @Override
    public Class<NGAP_QosFlowAddOrModifyRequestItem> getItemType() {
        return NGAP_QosFlowAddOrModifyRequestItem.class;
    }
}
