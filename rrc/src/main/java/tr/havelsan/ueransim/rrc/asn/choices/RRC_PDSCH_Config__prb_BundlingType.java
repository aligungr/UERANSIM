/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_PDSCH_Config__prb_BundlingType__dynamicBundling;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_PDSCH_Config__prb_BundlingType__staticBundling;

public class RRC_PDSCH_Config__prb_BundlingType extends RRC_Choice {

    public RRC_PDSCH_Config__prb_BundlingType__staticBundling staticBundling;
    public RRC_PDSCH_Config__prb_BundlingType__dynamicBundling dynamicBundling;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "staticBundling","dynamicBundling" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "staticBundling","dynamicBundling" };
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
