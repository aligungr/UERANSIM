package tr.havelsan.ueransim.ngap4.ies.sequences;

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

public class NGAP_OverloadStartNSSAIItem extends NGAP_Sequence {

    public NGAP_SliceOverloadList sliceOverloadList;
    public NGAP_OverloadResponse sliceOverloadResponse;
    public NGAP_TrafficLoadReductionIndication sliceTrafficLoadReductionIndication;

    @Override
    public String getAsnName() {
        return "OverloadStartNSSAIItem";
    }

    @Override
    public String getXmlTagName() {
        return "OverloadStartNSSAIItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"sliceOverloadList", "sliceOverloadResponse", "sliceTrafficLoadReductionIndication"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"sliceOverloadList", "sliceOverloadResponse", "sliceTrafficLoadReductionIndication"};
    }
}
