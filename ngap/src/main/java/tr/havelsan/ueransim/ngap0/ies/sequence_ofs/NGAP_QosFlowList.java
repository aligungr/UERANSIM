package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

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

public class NGAP_QosFlowList extends NGAP_SequenceOf<NGAP_QosFlowItem> {

    public NGAP_QosFlowList() {
        super();
    }

    public NGAP_QosFlowList(List<NGAP_QosFlowItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowList";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowList";
    }

    @Override
    public Class<NGAP_QosFlowItem> getItemType() {
        return NGAP_QosFlowItem.class;
    }
}
