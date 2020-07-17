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

public class NGAP_PacketErrorRate extends NGAP_Sequence {

    public NGAP_Integer pERScalar;
    public NGAP_Integer pERExponent;

    @Override
    public String getAsnName() {
        return "PacketErrorRate";
    }

    @Override
    public String getXmlTagName() {
        return "PacketErrorRate";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pERScalar", "pERExponent"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pERScalar", "pERExponent"};
    }
}
