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

public class NGAP_QosFlowList extends NgapSequenceOf<NGAP_QosFlowItem> {

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
