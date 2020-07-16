package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.NGAP_EUTRAencryptionAlgorithms;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.NGAP_EUTRAintegrityProtectionAlgorithms;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.NGAP_NRencryptionAlgorithms;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.NGAP_NRintegrityProtectionAlgorithms;

public class NGAP_UESecurityCapabilities extends NgapSequence {

    public NGAP_NRencryptionAlgorithms nRencryptionAlgorithms;
    public NGAP_NRintegrityProtectionAlgorithms nRintegrityProtectionAlgorithms;
    public NGAP_EUTRAencryptionAlgorithms eUTRAencryptionAlgorithms;
    public NGAP_EUTRAintegrityProtectionAlgorithms eUTRAintegrityProtectionAlgorithms;

    @Override
    protected String getAsnName() {
        return "UESecurityCapabilities";
    }

    @Override
    protected String getXmlTagName() {
        return "UESecurityCapabilities";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"nRencryptionAlgorithms", "nRintegrityProtectionAlgorithms", "eUTRAencryptionAlgorithms", "eUTRAintegrityProtectionAlgorithms"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"nRencryptionAlgorithms", "nRintegrityProtectionAlgorithms", "eUTRAencryptionAlgorithms", "eUTRAintegrityProtectionAlgorithms"};
    }
}
