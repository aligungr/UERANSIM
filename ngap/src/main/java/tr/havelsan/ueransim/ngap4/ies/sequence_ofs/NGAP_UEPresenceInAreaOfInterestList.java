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

public class NGAP_UEPresenceInAreaOfInterestList extends NgapSequenceOf<NGAP_UEPresenceInAreaOfInterestItem> {

    public NGAP_UEPresenceInAreaOfInterestList() {
        super();
    }

    public NGAP_UEPresenceInAreaOfInterestList(List<NGAP_UEPresenceInAreaOfInterestItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "UEPresenceInAreaOfInterestList";
    }

    @Override
    public String getXmlTagName() {
        return "UEPresenceInAreaOfInterestList";
    }

    @Override
    public Class<NGAP_UEPresenceInAreaOfInterestItem> getItemType() {
        return NGAP_UEPresenceInAreaOfInterestItem.class;
    }
}
