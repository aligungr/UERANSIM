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

public class NGAP_QosFlowAcceptedList extends NgapSequenceOf<NGAP_QosFlowAcceptedItem> {

    public NGAP_QosFlowAcceptedList() {
        super();
    }

    public NGAP_QosFlowAcceptedList(List<NGAP_QosFlowAcceptedItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowAcceptedList";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowAcceptedList";
    }

    @Override
    public Class<NGAP_QosFlowAcceptedItem> getItemType() {
        return NGAP_QosFlowAcceptedItem.class;
    }
}
