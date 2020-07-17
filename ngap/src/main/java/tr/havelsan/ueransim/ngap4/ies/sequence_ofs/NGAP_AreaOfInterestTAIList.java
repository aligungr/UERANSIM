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

public class NGAP_AreaOfInterestTAIList extends NGAP_SequenceOf<NGAP_AreaOfInterestTAIItem> {

    public NGAP_AreaOfInterestTAIList() {
        super();
    }

    public NGAP_AreaOfInterestTAIList(List<NGAP_AreaOfInterestTAIItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "AreaOfInterestTAIList";
    }

    @Override
    public String getXmlTagName() {
        return "AreaOfInterestTAIList";
    }

    @Override
    public Class<NGAP_AreaOfInterestTAIItem> getItemType() {
        return NGAP_AreaOfInterestTAIItem.class;
    }
}
