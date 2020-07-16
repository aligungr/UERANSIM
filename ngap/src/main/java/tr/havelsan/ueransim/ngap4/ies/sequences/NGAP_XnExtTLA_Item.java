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

public class NGAP_XnExtTLA_Item extends NgapSequence {

    public NGAP_TransportLayerAddress iPsecTLA;
    public NGAP_XnGTP_TLAs gTP_TLAs;

    @Override
    protected String getAsnName() {
        return "XnExtTLA-Item";
    }

    @Override
    protected String getXmlTagName() {
        return "XnExtTLA-Item";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"iPsecTLA", "gTP-TLAs"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"iPsecTLA", "gTP_TLAs"};
    }
}
