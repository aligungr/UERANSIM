/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SRS_Resource__resourceType;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SRS_Resource__transmissionComb;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SRS_ResourceId;

public class RRC_SRS_Resource extends RRC_Sequence {

    public RRC_SRS_ResourceId srs_ResourceId;
    public RRC_Integer nrofSRS_Ports;
    public RRC_Integer ptrs_PortIndex;
    public RRC_SRS_Resource__transmissionComb transmissionComb;
    public RRC_SRS_Resource__resourceMapping resourceMapping;
    public RRC_Integer freqDomainPosition;
    public RRC_Integer freqDomainShift;
    public RRC_SRS_Resource__freqHopping freqHopping;
    public RRC_Integer groupOrSequenceHopping;
    public RRC_SRS_Resource__resourceType resourceType;
    public RRC_Integer sequenceId;
    public RRC_SRS_SpatialRelationInfo spatialRelationInfo;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "srs-ResourceId","nrofSRS-Ports","ptrs-PortIndex","transmissionComb","resourceMapping","freqDomainPosition","freqDomainShift","freqHopping","groupOrSequenceHopping","resourceType","sequenceId","spatialRelationInfo" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "srs_ResourceId","nrofSRS_Ports","ptrs_PortIndex","transmissionComb","resourceMapping","freqDomainPosition","freqDomainShift","freqHopping","groupOrSequenceHopping","resourceType","sequenceId","spatialRelationInfo" };
    }

    @Override
    public String getAsnName() {
        return "SRS-Resource";
    }

    @Override
    public String getXmlTagName() {
        return "SRS-Resource";
    }

}
