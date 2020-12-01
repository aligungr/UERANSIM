/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_DummyC extends RRC_Sequence {

    public RRC_Integer maxNumberTxPortsPerResource;
    public RRC_Integer maxNumberResources;
    public RRC_Integer totalNumberTxPorts;
    public RRC_Integer supportedCodebookMode;
    public RRC_Integer supportedNumberPanels;
    public RRC_Integer maxNumberCSI_RS_PerResourceSet;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "maxNumberTxPortsPerResource","maxNumberResources","totalNumberTxPorts","supportedCodebookMode","supportedNumberPanels","maxNumberCSI-RS-PerResourceSet" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "maxNumberTxPortsPerResource","maxNumberResources","totalNumberTxPorts","supportedCodebookMode","supportedNumberPanels","maxNumberCSI_RS_PerResourceSet" };
    }

    @Override
    public String getAsnName() {
        return "DummyC";
    }

    @Override
    public String getXmlTagName() {
        return "DummyC";
    }

}
