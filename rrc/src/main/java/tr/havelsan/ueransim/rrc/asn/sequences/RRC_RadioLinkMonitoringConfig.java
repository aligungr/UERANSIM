/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_RadioLinkMonitoringConfig__failureDetectionResourcesToAddModList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_RadioLinkMonitoringConfig__failureDetectionResourcesToReleaseList;

public class RRC_RadioLinkMonitoringConfig extends RRC_Sequence {

    public RRC_RadioLinkMonitoringConfig__failureDetectionResourcesToAddModList failureDetectionResourcesToAddModList;
    public RRC_RadioLinkMonitoringConfig__failureDetectionResourcesToReleaseList failureDetectionResourcesToReleaseList;
    public RRC_Integer beamFailureInstanceMaxCount;
    public RRC_Integer beamFailureDetectionTimer;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "failureDetectionResourcesToAddModList","failureDetectionResourcesToReleaseList","beamFailureInstanceMaxCount","beamFailureDetectionTimer" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "failureDetectionResourcesToAddModList","failureDetectionResourcesToReleaseList","beamFailureInstanceMaxCount","beamFailureDetectionTimer" };
    }

    @Override
    public String getAsnName() {
        return "RadioLinkMonitoringConfig";
    }

    @Override
    public String getXmlTagName() {
        return "RadioLinkMonitoringConfig";
    }

}
