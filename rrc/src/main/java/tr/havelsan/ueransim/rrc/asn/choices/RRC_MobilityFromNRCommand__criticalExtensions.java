/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_MobilityFromNRCommand_IEs;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_MobilityFromNRCommand__criticalExtensions__criticalExtensionsFuture;

public class RRC_MobilityFromNRCommand__criticalExtensions extends RRC_Choice {

    public RRC_MobilityFromNRCommand_IEs mobilityFromNRCommand;
    public RRC_MobilityFromNRCommand__criticalExtensions__criticalExtensionsFuture criticalExtensionsFuture;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "mobilityFromNRCommand","criticalExtensionsFuture" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "mobilityFromNRCommand","criticalExtensionsFuture" };
    }

}
