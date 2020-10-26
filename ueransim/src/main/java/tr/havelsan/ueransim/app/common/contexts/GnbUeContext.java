/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package tr.havelsan.ueransim.app.common.contexts;

import tr.havelsan.ueransim.app.common.Guami;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_MaskedIMEISV;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_SecurityKey;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_PagingPriority;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_IndexToRFSP;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_UERadioCapability;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_MobilityRestrictionList;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_UEAggregateMaximumBitRate;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_UESecurityCapabilities;

import java.util.UUID;

public class GnbUeContext {
    public final UUID ueCtxId;

    public Long amfUeNgapId;
    public long ranUeNgapId;
    public Guami associatedAmf;
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

    public GnbUeContext(UUID ueCtxId) {
        this.ueCtxId = ueCtxId;
    }
}
