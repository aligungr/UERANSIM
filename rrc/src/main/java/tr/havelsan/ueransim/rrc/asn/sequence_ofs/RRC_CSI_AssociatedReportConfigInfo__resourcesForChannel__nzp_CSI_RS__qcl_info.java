/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_TCI_StateId;

public class RRC_CSI_AssociatedReportConfigInfo__resourcesForChannel__nzp_CSI_RS__qcl_info extends RRC_SequenceOf<RRC_TCI_StateId> {

    @Override
    public Class<RRC_TCI_StateId> getItemType() {
        return RRC_TCI_StateId.class;
    }

}
