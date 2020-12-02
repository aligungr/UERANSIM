/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CounterCheckResponse_IEs;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CounterCheckResponse__criticalExtensions__criticalExtensionsFuture;

public class RRC_CounterCheckResponse__criticalExtensions extends RRC_Choice {

    public RRC_CounterCheckResponse_IEs counterCheckResponse;
    public RRC_CounterCheckResponse__criticalExtensions__criticalExtensionsFuture criticalExtensionsFuture;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "counterCheckResponse","criticalExtensionsFuture" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "counterCheckResponse","criticalExtensionsFuture" };
    }

}
