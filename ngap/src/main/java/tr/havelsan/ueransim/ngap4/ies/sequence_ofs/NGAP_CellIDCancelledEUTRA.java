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

public class NGAP_CellIDCancelledEUTRA extends NGAP_SequenceOf<NGAP_CellIDCancelledEUTRA_Item> {

    public NGAP_CellIDCancelledEUTRA() {
        super();
    }

    public NGAP_CellIDCancelledEUTRA(List<NGAP_CellIDCancelledEUTRA_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "CellIDCancelledEUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "CellIDCancelledEUTRA";
    }

    @Override
    public Class<NGAP_CellIDCancelledEUTRA_Item> getItemType() {
        return NGAP_CellIDCancelledEUTRA_Item.class;
    }
}
