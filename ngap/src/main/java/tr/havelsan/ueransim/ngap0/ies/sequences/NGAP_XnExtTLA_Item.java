package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;

public class NGAP_XnExtTLA_Item extends NGAP_Sequence {

    public NGAP_TransportLayerAddress iPsecTLA;
    public NGAP_XnGTP_TLAs gTP_TLAs;

    @Override
    public String getAsnName() {
        return "XnExtTLA-Item";
    }

    @Override
    public String getXmlTagName() {
        return "XnExtTLA-Item";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"iPsecTLA", "gTP-TLAs"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"iPsecTLA", "gTP_TLAs"};
    }
}
