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

package tr.havelsan.ueransim.app.common;

import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_UPTransportLayerInformation;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_DataForwardingNotPossible;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_PDUSessionType;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_NetworkInstance;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionAggregateMaximumBitRate;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_QosFlowSetupRequestItem;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_SecurityIndication;

import java.util.List;
import java.util.UUID;

public class PduSessionResource {
    public UUID ueId;
    public int pduSessionId;

    public NGAP_PDUSessionAggregateMaximumBitRate aggregateMaximumBitRate;
    public NGAP_DataForwardingNotPossible dataForwardingNotPossible;
    public NGAP_PDUSessionType type;
    public NGAP_SecurityIndication securityIndication;
    public NGAP_NetworkInstance networkInstance;
    public NGAP_UPTransportLayerInformation upLayer;
    public NGAP_UPTransportLayerInformation downLayer;
    public List<NGAP_QosFlowSetupRequestItem> qosFlows;
}
