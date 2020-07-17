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

public class NGAP_CellIDBroadcastEUTRA extends NgapSequenceOf<NGAP_CellIDBroadcastEUTRA_Item> {

    public NGAP_CellIDBroadcastEUTRA() {
        super();
    }

    public NGAP_CellIDBroadcastEUTRA(List<NGAP_CellIDBroadcastEUTRA_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "CellIDBroadcastEUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "CellIDBroadcastEUTRA";
    }

    @Override
    public Class<NGAP_CellIDBroadcastEUTRA_Item> getItemType() {
        return NGAP_CellIDBroadcastEUTRA_Item.class;
    }
}
