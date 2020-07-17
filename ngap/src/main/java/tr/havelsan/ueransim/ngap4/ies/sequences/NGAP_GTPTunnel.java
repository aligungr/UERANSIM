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

public class NGAP_GTPTunnel extends NGAP_Sequence {

    public NGAP_TransportLayerAddress transportLayerAddress;
    public NGAP_GTP_TEID gTP_TEID;

    @Override
    public String getAsnName() {
        return "GTPTunnel";
    }

    @Override
    public String getXmlTagName() {
        return "GTPTunnel";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"transportLayerAddress", "gTP-TEID"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"transportLayerAddress", "gTP_TEID"};
    }
}
