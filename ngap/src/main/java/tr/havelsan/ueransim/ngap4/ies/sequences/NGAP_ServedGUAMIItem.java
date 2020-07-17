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

public class NGAP_ServedGUAMIItem extends NgapSequence {

    public NGAP_GUAMI gUAMI;
    public NGAP_AMFName backupAMFName;

    @Override
    public String getAsnName() {
        return "ServedGUAMIItem";
    }

    @Override
    public String getXmlTagName() {
        return "ServedGUAMIItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"gUAMI", "backupAMFName"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"gUAMI", "backupAMFName"};
    }
}
