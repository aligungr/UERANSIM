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

public class NGAP_QosFlowSetupResponseListHOReqAck extends NgapSequenceOf<NGAP_QosFlowSetupResponseItemHOReqAck> {

    public NGAP_QosFlowSetupResponseListHOReqAck() {
        super();
    }

    public NGAP_QosFlowSetupResponseListHOReqAck(List<NGAP_QosFlowSetupResponseItemHOReqAck> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowSetupResponseListHOReqAck";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowSetupResponseListHOReqAck";
    }

    @Override
    public Class<NGAP_QosFlowSetupResponseItemHOReqAck> getItemType() {
        return NGAP_QosFlowSetupResponseItemHOReqAck.class;
    }
}
