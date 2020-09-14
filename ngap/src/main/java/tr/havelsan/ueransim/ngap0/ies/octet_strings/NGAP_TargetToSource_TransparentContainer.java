package tr.havelsan.ueransim.ngap0.ies.octet_strings;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.pdu.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

import java.util.List;

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
