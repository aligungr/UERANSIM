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

import java.util.List;

public class NGAP_QosFlowModifyConfirmList extends NGAP_SequenceOf<NGAP_QosFlowModifyConfirmItem> {

    public NGAP_QosFlowModifyConfirmList() {
        super();
    }

    public NGAP_QosFlowModifyConfirmList(List<NGAP_QosFlowModifyConfirmItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowModifyConfirmList";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowModifyConfirmList";
    }

    @Override
    public Class<NGAP_QosFlowModifyConfirmItem> getItemType() {
        return NGAP_QosFlowModifyConfirmItem.class;
    }
}
