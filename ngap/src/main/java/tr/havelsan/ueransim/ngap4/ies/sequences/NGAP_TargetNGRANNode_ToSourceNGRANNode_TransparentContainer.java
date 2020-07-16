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

public class NGAP_TargetNGRANNode_ToSourceNGRANNode_TransparentContainer extends NgapSequence {

    public NGAP_RRCContainer rRCContainer;

    @Override
    protected String getAsnName() {
        return "TargetNGRANNode-ToSourceNGRANNode-TransparentContainer";
    }

    @Override
    protected String getXmlTagName() {
        return "TargetNGRANNode-ToSourceNGRANNode-TransparentContainer";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"rRCContainer"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"rRCContainer"};
    }
}
