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

public class NGAP_CompletedCellsInTAI_NR extends NgapSequenceOf<NGAP_CompletedCellsInTAI_NR_Item> {

    public NGAP_CompletedCellsInTAI_NR() {
        super();
    }

    public NGAP_CompletedCellsInTAI_NR(List<NGAP_CompletedCellsInTAI_NR_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "CompletedCellsInTAI-NR";
    }

    @Override
    public String getXmlTagName() {
        return "CompletedCellsInTAI-NR";
    }

    @Override
    public Class<NGAP_CompletedCellsInTAI_NR_Item> getItemType() {
        return NGAP_CompletedCellsInTAI_NR_Item.class;
    }
}
