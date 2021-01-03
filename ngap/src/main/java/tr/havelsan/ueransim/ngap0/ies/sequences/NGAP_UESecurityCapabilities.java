/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_EUTRAencryptionAlgorithms;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_EUTRAintegrityProtectionAlgorithms;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_NRencryptionAlgorithms;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_NRintegrityProtectionAlgorithms;

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
