/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_SupportedCSI_RS_Resource;

public class RRC_CodebookParameters__type2_PortSelection__supportedCSI_RS_ResourceList extends RRC_SequenceOf<RRC_SupportedCSI_RS_Resource> {

    @Override
    public Class<RRC_SupportedCSI_RS_Resource> getItemType() {
        return RRC_SupportedCSI_RS_Resource.class;
    }

}
