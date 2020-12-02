/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RRCReconfiguration_IEs;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RRCReconfiguration__criticalExtensions__criticalExtensionsFuture;

public class RRC_RRCReconfiguration__criticalExtensions extends RRC_Choice {

    public RRC_RRCReconfiguration_IEs rrcReconfiguration;
    public RRC_RRCReconfiguration__criticalExtensions__criticalExtensionsFuture criticalExtensionsFuture;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "rrcReconfiguration","criticalExtensionsFuture" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "rrcReconfiguration","criticalExtensionsFuture" };
    }

}
