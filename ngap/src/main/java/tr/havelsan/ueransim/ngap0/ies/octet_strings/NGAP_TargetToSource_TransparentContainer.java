package tr.havelsan.ueransim.ngap0.ies.octet_strings;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_TargetToSource_TransparentContainer extends NGAP_OctetString {

    public NGAP_TargetToSource_TransparentContainer(OctetString value) {
        super(value);
    }

    public NGAP_TargetToSource_TransparentContainer(BitString value) {
        super(value);
    }

    public NGAP_TargetToSource_TransparentContainer(Octet[] octets) {
        super(octets);
    }

    public NGAP_TargetToSource_TransparentContainer(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_TargetToSource_TransparentContainer(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_TargetToSource_TransparentContainer(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "TargetToSource-TransparentContainer";
    }

    @Override
    public String getXmlTagName() {
        return "TargetToSource-TransparentContainer";
    }
}
