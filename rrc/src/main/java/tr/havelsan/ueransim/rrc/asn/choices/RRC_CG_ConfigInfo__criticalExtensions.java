/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CG_ConfigInfo__criticalExtensions__criticalExtensionsFuture;

public class RRC_CG_ConfigInfo__criticalExtensions extends RRC_Choice {

    public RRC_CG_ConfigInfo__criticalExtensions__c1 c1;
    public RRC_CG_ConfigInfo__criticalExtensions__criticalExtensionsFuture criticalExtensionsFuture;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "c1","criticalExtensionsFuture" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "c1","criticalExtensionsFuture" };
    }

}
