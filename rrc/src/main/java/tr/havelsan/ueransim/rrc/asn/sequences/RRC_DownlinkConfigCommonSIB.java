package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_DownlinkConfigCommonSIB extends AsnSequence {
    public RRC_FrequencyInfoDL_SIB frequencyInfoDL; // mandatory
    public RRC_BWP_DownlinkCommon initialDownlinkBWP; // mandatory
    public RRC_BCCH_Config bcch_Config; // mandatory
    public RRC_PCCH_Config pcch_Config; // mandatory
}

