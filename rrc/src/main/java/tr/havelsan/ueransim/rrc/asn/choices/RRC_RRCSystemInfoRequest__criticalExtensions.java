/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RRCSystemInfoRequest__criticalExtensions__criticalExtensionsFuture;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RRCSystemInfoRequest_r15_IEs;

public class RRC_RRCSystemInfoRequest__criticalExtensions extends RRC_Choice {

    public RRC_RRCSystemInfoRequest_r15_IEs rrcSystemInfoRequest_r15;
    public RRC_RRCSystemInfoRequest__criticalExtensions__criticalExtensionsFuture criticalExtensionsFuture;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "rrcSystemInfoRequest-r15","criticalExtensionsFuture" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "rrcSystemInfoRequest_r15","criticalExtensionsFuture" };
    }

}
