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

public class NGAP_QosFlowNotifyList extends NgapSequenceOf<NGAP_QosFlowNotifyItem> {

    public NGAP_QosFlowNotifyList() {
        super();
    }

    public NGAP_QosFlowNotifyList(List<NGAP_QosFlowNotifyItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowNotifyList";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowNotifyList";
    }

    @Override
    public Class<NGAP_QosFlowNotifyItem> getItemType() {
        return NGAP_QosFlowNotifyItem.class;
    }
}
