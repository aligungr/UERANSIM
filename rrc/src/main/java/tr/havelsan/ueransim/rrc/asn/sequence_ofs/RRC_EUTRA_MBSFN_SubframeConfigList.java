/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_EUTRA_MBSFN_SubframeConfig;

public class RRC_EUTRA_MBSFN_SubframeConfigList extends RRC_SequenceOf<RRC_EUTRA_MBSFN_SubframeConfig> {

    @Override
    public String getAsnName() {
        return "EUTRA-MBSFN-SubframeConfigList";
    }

    @Override
    public String getXmlTagName() {
        return "EUTRA-MBSFN-SubframeConfigList";
    }

    @Override
    public Class<RRC_EUTRA_MBSFN_SubframeConfig> getItemType() {
        return RRC_EUTRA_MBSFN_SubframeConfig.class;
    }

}
