/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RadioLinkMonitoringRS;

public class RRC_RadioLinkMonitoringConfig__failureDetectionResourcesToAddModList extends RRC_SequenceOf<RRC_RadioLinkMonitoringRS> {

    @Override
    public Class<RRC_RadioLinkMonitoringRS> getItemType() {
        return RRC_RadioLinkMonitoringRS.class;
    }

}
