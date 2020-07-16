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

public class NGAP_PDUSessionResourceModifyUnsuccessfulTransfer extends NgapSequence {

    public NGAP_Cause cause;
    public NGAP_CriticalityDiagnostics criticalityDiagnostics;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceModifyUnsuccessfulTransfer";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceModifyUnsuccessfulTransfer";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"cause", "criticalityDiagnostics"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"cause", "criticalityDiagnostics"};
    }
}
