/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_SCGFailureInformation_IEs;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_SCGFailureInformation__criticalExtensions__criticalExtensionsFuture;

public class RRC_SCGFailureInformation__criticalExtensions extends RRC_Choice {

    public RRC_SCGFailureInformation_IEs scgFailureInformation;
    public RRC_SCGFailureInformation__criticalExtensions__criticalExtensionsFuture criticalExtensionsFuture;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "scgFailureInformation","criticalExtensionsFuture" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "scgFailureInformation","criticalExtensionsFuture" };
    }

}
