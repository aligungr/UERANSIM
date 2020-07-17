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

public class NGAP_QosFlowToBeForwardedList extends NgapSequenceOf<NGAP_QosFlowToBeForwardedItem> {

    public NGAP_QosFlowToBeForwardedList() {
        super();
    }

    public NGAP_QosFlowToBeForwardedList(List<NGAP_QosFlowToBeForwardedItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowToBeForwardedList";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowToBeForwardedList";
    }

    @Override
    public Class<NGAP_QosFlowToBeForwardedItem> getItemType() {
        return NGAP_QosFlowToBeForwardedItem.class;
    }
}
