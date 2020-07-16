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

public class NGAP_CompletedCellsInEAI_NR extends NgapSequenceOf<NGAP_CompletedCellsInEAI_NR_Item> {

    public NGAP_CompletedCellsInEAI_NR() {
        super();
    }

    public NGAP_CompletedCellsInEAI_NR(List<NGAP_CompletedCellsInEAI_NR_Item> value) {
        super(value);
    }

    @Override
    protected String getAsnName() {
        return "CompletedCellsInEAI-NR";
    }

    @Override
    protected String getXmlTagName() {
        return "CompletedCellsInEAI-NR";
    }

    @Override
    public Class<NGAP_CompletedCellsInEAI_NR_Item> getItemType() {
        return NGAP_CompletedCellsInEAI_NR_Item.class;
    }
}
