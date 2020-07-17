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

public class NGAP_CellIDBroadcastNR extends NGAP_SequenceOf<NGAP_CellIDBroadcastNR_Item> {

    public NGAP_CellIDBroadcastNR() {
        super();
    }

    public NGAP_CellIDBroadcastNR(List<NGAP_CellIDBroadcastNR_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "CellIDBroadcastNR";
    }

    @Override
    public String getXmlTagName() {
        return "CellIDBroadcastNR";
    }

    @Override
    public Class<NGAP_CellIDBroadcastNR_Item> getItemType() {
        return NGAP_CellIDBroadcastNR_Item.class;
    }
}
