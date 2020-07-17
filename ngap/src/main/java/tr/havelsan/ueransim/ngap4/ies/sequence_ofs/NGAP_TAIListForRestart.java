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

public class NGAP_TAIListForRestart extends NgapSequenceOf<NGAP_TAI> {

    public NGAP_TAIListForRestart() {
        super();
    }

    public NGAP_TAIListForRestart(List<NGAP_TAI> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "TAIListForRestart";
    }

    @Override
    public String getXmlTagName() {
        return "TAIListForRestart";
    }

    @Override
    public Class<NGAP_TAI> getItemType() {
        return NGAP_TAI.class;
    }
}
