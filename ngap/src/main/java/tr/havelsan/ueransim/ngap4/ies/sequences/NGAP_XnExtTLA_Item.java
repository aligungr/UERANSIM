package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.NGAP_TransportLayerAddress;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_XnGTP_TLAs;

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
