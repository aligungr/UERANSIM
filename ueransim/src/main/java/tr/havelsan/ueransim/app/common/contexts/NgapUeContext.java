/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.common.contexts;

import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_MaskedIMEISV;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_SecurityKey;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_PagingPriority;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_IndexToRFSP;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_UERadioCapability;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_MobilityRestrictionList;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_UEAggregateMaximumBitRate;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_UESecurityCapabilities;

import java.util.UUID;

public class NgapUeContext {
    public final UUID ueCtxId;

    public Long amfUeNgapId;
    public long ranUeNgapId;
    public UUID associatedAmf;
    public int uplinkStream;
    public int downlinkStream;

    public NGAP_IndexToRFSP indexToRfsp;
    public NGAP_MaskedIMEISV maskedImeiSv;
    public NGAP_UEAggregateMaximumBitRate aggregateMaximumBitRate;
    public NGAP_MobilityRestrictionList mobilityRestrictions;
    public NGAP_UERadioCapability radioCapability;
    public NGAP_UESecurityCapabilities securityCapabilities;
    public NGAP_SecurityKey securityKey;
    public NGAP_PagingPriority pagingPriority;

    public NgapUeContext(UUID ueCtxId) {
        this.ueCtxId = ueCtxId;
    }
}
