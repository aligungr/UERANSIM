/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_UE_CapabilityRAT_Container;

public class RRC_UE_CapabilityRAT_ContainerList extends RRC_SequenceOf<RRC_UE_CapabilityRAT_Container> {

    @Override
    public String getAsnName() {
        return "UE-CapabilityRAT-ContainerList";
    }

    @Override
    public String getXmlTagName() {
        return "UE-CapabilityRAT-ContainerList";
    }

    @Override
    public Class<RRC_UE_CapabilityRAT_Container> getItemType() {
        return RRC_UE_CapabilityRAT_Container.class;
    }

}
