/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_SI_RequestResources;

public class RRC_SI_RequestConfig__si_RequestResources extends RRC_SequenceOf<RRC_SI_RequestResources> {

    @Override
    public Class<RRC_SI_RequestResources> getItemType() {
        return RRC_SI_RequestResources.class;
    }

}
