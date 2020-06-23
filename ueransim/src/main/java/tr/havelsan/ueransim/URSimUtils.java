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
 *
 * @author Ali Güngör (aligng1620@gmail.com)
 */

package tr.havelsan.ueransim;

import fr.marben.asnsdk.japi.InvalidStructureException;
import fr.marben.asnsdk.japi.spe.BitStringValue;
import tr.havelsan.ueransim.nas.impl.ies.IESNssai;
import tr.havelsan.ueransim.nas.impl.values.VPlmn;
import tr.havelsan.ueransim.ngap.ngap_ies.*;
import tr.havelsan.ueransim.ngap2.SupportedTA;
import tr.havelsan.ueransim.utils.octets.Octet4;

import java.util.ArrayList;

public class URSimUtils {

    public static GlobalRANNodeID createGlobalGnbId(int globalGnbId, VPlmn gnbPlmn) {
        try {
            var res = new GlobalGNB_ID();
            res.gNB_ID =
                    new GNB_ID(
                            GNB_ID.ASN_gNB_ID, new BitStringValue(new Octet4(globalGnbId).toByteArray(true), 32));
            res.pLMNIdentity = Ngap.plmnEncode(gnbPlmn);
            return new GlobalRANNodeID(GlobalRANNodeID.ASN_globalGNB_ID, res);
        } catch (InvalidStructureException e) {
            throw new RuntimeException(e);
        }
    }

    private static SliceSupportList createSliceSupportList(IESNssai[] taiSliceSupportNssais) {
        var list = new ArrayList<SliceSupportItem>();

        if (taiSliceSupportNssais != null) {
            for (var nssai : taiSliceSupportNssais) {
                var item = new SliceSupportItem();
                item.s_NSSAI = new S_NSSAI();
                item.s_NSSAI.sD = new SD(nssai.sd.value.toByteArray());
                item.s_NSSAI.sST = new SST(nssai.sst.value.toByteArray());
                list.add(item);
            }
        }

        var res = new SliceSupportList();
        res.valueList = list;
        return res;
    }

    private static BroadcastPLMNList createBroadcastPlmnList(
            SupportedTA.BroadcastPlmn[] broadcastPlmns) {
        var list = new ArrayList<BroadcastPLMNItem>();

        for (var broadcastPlmn : broadcastPlmns) {
            var item = new BroadcastPLMNItem();
            item.pLMNIdentity = Ngap.plmnEncode(broadcastPlmn.plmn);
            item.tAISliceSupportList = createSliceSupportList(broadcastPlmn.taiSliceSupportNssais);
            list.add(item);
        }

        var res = new BroadcastPLMNList();
        res.valueList = list;
        return res;
    }

    public static SupportedTAList createSupportedTAList(SupportedTA[] supportedTAs) {
        var list = new ArrayList<SupportedTAItem>();

        for (var supportedTa : supportedTAs) {
            var supportedTaiItem = new SupportedTAItem();
            supportedTaiItem.tAC = new TAC(supportedTa.tac.toByteArray());
            supportedTaiItem.broadcastPLMNList = createBroadcastPlmnList(supportedTa.broadcastPlmns);
            list.add(supportedTaiItem);
        }

        var res = new SupportedTAList();
        res.valueList = list;
        return res;
    }
}