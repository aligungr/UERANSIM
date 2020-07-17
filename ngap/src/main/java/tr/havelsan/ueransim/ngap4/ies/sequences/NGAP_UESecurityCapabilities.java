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

public class NGAP_UESecurityCapabilities extends NGAP_Sequence {

    public NGAP_NRencryptionAlgorithms nRencryptionAlgorithms;
    public NGAP_NRintegrityProtectionAlgorithms nRintegrityProtectionAlgorithms;
    public NGAP_EUTRAencryptionAlgorithms eUTRAencryptionAlgorithms;
    public NGAP_EUTRAintegrityProtectionAlgorithms eUTRAintegrityProtectionAlgorithms;

    @Override
    public String getAsnName() {
        return "UESecurityCapabilities";
    }

    @Override
    public String getXmlTagName() {
        return "UESecurityCapabilities";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"nRencryptionAlgorithms", "nRintegrityProtectionAlgorithms", "eUTRAencryptionAlgorithms", "eUTRAintegrityProtectionAlgorithms"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"nRencryptionAlgorithms", "nRintegrityProtectionAlgorithms", "eUTRAencryptionAlgorithms", "eUTRAintegrityProtectionAlgorithms"};
    }
}
