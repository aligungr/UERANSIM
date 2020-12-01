/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_SupportedCSI_RS_Resource extends RRC_Sequence {

    public RRC_Integer maxNumberTxPortsPerResource;
    public RRC_Integer maxNumberResourcesPerBand;
    public RRC_Integer totalNumberTxPortsPerBand;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "maxNumberTxPortsPerResource","maxNumberResourcesPerBand","totalNumberTxPortsPerBand" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "maxNumberTxPortsPerResource","maxNumberResourcesPerBand","totalNumberTxPortsPerBand" };
    }

    @Override
    public String getAsnName() {
        return "SupportedCSI-RS-Resource";
    }

    @Override
    public String getXmlTagName() {
        return "SupportedCSI-RS-Resource";
    }

}
