/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_SRS_Resource__resourceType__aperiodic;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_SRS_Resource__resourceType__periodic;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_SRS_Resource__resourceType__semi_persistent;

public class RRC_SRS_Resource__resourceType extends RRC_Choice {

    public RRC_SRS_Resource__resourceType__aperiodic aperiodic;
    public RRC_SRS_Resource__resourceType__semi_persistent semi_persistent;
    public RRC_SRS_Resource__resourceType__periodic periodic;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "aperiodic","semi-persistent","periodic" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "aperiodic","semi_persistent","periodic" };
    }

}
