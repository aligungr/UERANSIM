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

public class NGAP_DRBStatusDL12 extends NgapSequence {

    public NGAP_COUNTValueForPDCP_SN12 dL_COUNTValue;

    @Override
    protected String getAsnName() {
        return "DRBStatusDL12";
    }

    @Override
    protected String getXmlTagName() {
        return "DRBStatusDL12";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"dL-COUNTValue"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"dL_COUNTValue"};
    }
}
