/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_DummyD extends RRC_Sequence {

    public RRC_Integer maxNumberTxPortsPerResource;
    public RRC_Integer maxNumberResources;
    public RRC_Integer totalNumberTxPorts;
    public RRC_Integer parameterLx;
    public RRC_Integer amplitudeScalingType;
    public RRC_Integer amplitudeSubsetRestriction;
    public RRC_Integer maxNumberCSI_RS_PerResourceSet;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "maxNumberTxPortsPerResource","maxNumberResources","totalNumberTxPorts","parameterLx","amplitudeScalingType","amplitudeSubsetRestriction","maxNumberCSI-RS-PerResourceSet" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "maxNumberTxPortsPerResource","maxNumberResources","totalNumberTxPorts","parameterLx","amplitudeScalingType","amplitudeSubsetRestriction","maxNumberCSI_RS_PerResourceSet" };
    }

    @Override
    public String getAsnName() {
        return "DummyD";
    }

    @Override
    public String getXmlTagName() {
        return "DummyD";
    }

}
