/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_ConfiguredGrantConfig__rrc_ConfiguredUplinkGrant extends RRC_Sequence {

    public RRC_Integer timeDomainOffset;
    public RRC_Integer timeDomainAllocation;
    public RRC_BitString frequencyDomainAllocation;
    public RRC_Integer antennaPort;
    public RRC_Integer dmrs_SeqInitialization;
    public RRC_Integer precodingAndNumberOfLayers;
    public RRC_Integer srs_ResourceIndicator;
    public RRC_Integer mcsAndTBS;
    public RRC_Integer frequencyHoppingOffset;
    public RRC_Integer pathlossReferenceIndex;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "timeDomainOffset","timeDomainAllocation","frequencyDomainAllocation","antennaPort","dmrs-SeqInitialization","precodingAndNumberOfLayers","srs-ResourceIndicator","mcsAndTBS","frequencyHoppingOffset","pathlossReferenceIndex" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "timeDomainOffset","timeDomainAllocation","frequencyDomainAllocation","antennaPort","dmrs_SeqInitialization","precodingAndNumberOfLayers","srs_ResourceIndicator","mcsAndTBS","frequencyHoppingOffset","pathlossReferenceIndex" };
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
