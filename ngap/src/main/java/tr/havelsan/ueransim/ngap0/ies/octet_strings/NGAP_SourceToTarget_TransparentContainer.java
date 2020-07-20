package tr.havelsan.ueransim.ngap0.ies.octet_strings;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_SourceToTarget_TransparentContainer extends NGAP_OctetString {

    public NGAP_SourceToTarget_TransparentContainer(OctetString value) {
        super(value);
    }

    public NGAP_SourceToTarget_TransparentContainer(BitString value) {
        super(value);
    }

    public NGAP_SourceToTarget_TransparentContainer(Octet[] octets) {
        super(octets);
    }

    public NGAP_SourceToTarget_TransparentContainer(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_SourceToTarget_TransparentContainer(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_SourceToTarget_TransparentContainer(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "SourceToTarget-TransparentContainer";
    }

    @Override
    public String getXmlTagName() {
        return "SourceToTarget-TransparentContainer";
    }
}
