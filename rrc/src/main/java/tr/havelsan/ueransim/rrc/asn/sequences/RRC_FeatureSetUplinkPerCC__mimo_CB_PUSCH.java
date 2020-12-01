/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_MIMO_LayersUL;

public class RRC_FeatureSetUplinkPerCC__mimo_CB_PUSCH extends RRC_Sequence {

    public RRC_MIMO_LayersUL maxNumberMIMO_LayersCB_PUSCH;
    public RRC_Integer maxNumberSRS_ResourcePerSet;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "maxNumberMIMO-LayersCB-PUSCH","maxNumberSRS-ResourcePerSet" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "maxNumberMIMO_LayersCB_PUSCH","maxNumberSRS_ResourcePerSet" };
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

}
