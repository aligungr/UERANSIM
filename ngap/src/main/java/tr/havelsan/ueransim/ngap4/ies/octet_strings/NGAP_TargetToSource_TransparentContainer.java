package tr.havelsan.ueransim.ngap4.ies.octet_strings;

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

public class NGAP_TargetToSource_TransparentContainer extends NgapOctetString {

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
    protected String getAsnName() {
        return "TargetToSource-TransparentContainer";
    }

    @Override
    protected String getXmlTagName() {
        return "TargetToSource-TransparentContainer";
    }
}
