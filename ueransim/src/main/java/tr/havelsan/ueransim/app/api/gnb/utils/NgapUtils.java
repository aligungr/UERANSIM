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

package tr.havelsan.ueransim.app.api.gnb.utils;

import org.apache.commons.net.ntp.TimeStamp;
import tr.havelsan.ueransim.core.Constants;
import tr.havelsan.ueransim.core.exceptions.EncodingException;
import tr.havelsan.ueransim.nas.impl.enums.EMccValue;
import tr.havelsan.ueransim.nas.impl.enums.EMncValue;
import tr.havelsan.ueransim.nas.impl.ies.IESNssai;
import tr.havelsan.ueransim.nas.impl.values.VPlmn;
import tr.havelsan.ueransim.nas.impl.values.VTrackingAreaIdentity;
import tr.havelsan.ueransim.ngap0.core.NGAP_BitString;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_NRCellIdentity;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_GNB_ID;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_GlobalRANNodeID;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_BroadcastPLMNList;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_SliceSupportList;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_SupportedTAList;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet3;
import tr.havelsan.ueransim.utils.octets.Octet4;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NgapUtils {

    public static NGAP_PLMNIdentity plmnEncode(VPlmn plmn) {
        int mcc = plmn.mcc.intValue();
        int mcc3 = mcc % 10;
        int mcc2 = (mcc % 100) / 10;
        int mcc1 = (mcc % 1000) / 100;

        if (plmn.mnc == null)
            throw new EncodingException("mnc is null");

        int mnc = plmn.mnc.intValue();
        boolean longMnc = plmn.mnc.isLongMnc();

        if (Constants.USE_LONG_MNC)
            longMnc = true;

        if (longMnc) {
            int mnc1 = mnc % 1000 / 100;
            int mnc2 = mnc % 100 / 10;
            int mnc3 = mnc % 10;

            int octet1 = mcc2 << 4 | mcc1;
            int octet2 = mnc1 << 4 | mcc3;
            int octet3 = mnc3 << 4 | mnc2;

            return new NGAP_PLMNIdentity(new Octet3(octet1, octet2, octet3).toByteArray());
        } else {
            int mnc1 = mnc % 100 / 10;
            int mnc2 = mnc % 10;
            int mnc3 = 0xF;

            int octet1 = mcc2 << 4 | mcc1;
            int octet2 = mnc3 << 4 | mcc3;
            int octet3 = mnc2 << 4 | mnc1;

            return new NGAP_PLMNIdentity(new Octet3(octet1, octet2, octet3).toByteArray());
        }
    }

    public static VPlmn plmnDecode(NGAP_PLMNIdentity plmn) {
        var bytes = plmn.value.toByteArray();
        var stream = new OctetInputStream(bytes);

        var res = new VPlmn();

        /* Decode MCC */
        int octet1 = stream.readOctetI();
        int mcc1 = octet1 & 0b1111;
        int mcc2 = (octet1 >> 4) & 0b1111;
        int octet2 = stream.readOctetI();
        int mcc3 = octet2 & 0b1111;
        int mcc = 100 * mcc1 + 10 * mcc2 + mcc3;
        res.mcc = EMccValue.fromValue(mcc);

        /* Decode MNC */
        int mnc3 = (octet2 >> 4) & 0b1111;
        int octet3 = stream.readOctetI();
        int mnc1 = octet3 & 0b1111;
        int mnc2 = (octet3 >> 4) & 0b1111;

        int mnc = 10 * mnc3 + mnc1;
        if ((mnc3 != 0xf) || (octet1 == 0xff && octet2 == 0xff && octet3 == 0xff)) {
            mnc = 10 * mnc + mnc2;
        }
        res.mnc = EMncValue.fromValue(mnc);
        return res;
    }

    public static NGAP_UserLocationInformationNR createUserLocationInformationNr(VPlmn plmn, VTrackingAreaIdentity tai, BitString nrCellIdentity) {
        var userLocationInformationNr = new NGAP_UserLocationInformationNR();
        userLocationInformationNr.nR_CGI = new NGAP_NR_CGI();
        userLocationInformationNr.nR_CGI.pLMNIdentity = NgapUtils.plmnEncode(plmn);
        userLocationInformationNr.nR_CGI.nRCellIdentity = new NGAP_NRCellIdentity(nrCellIdentity);
        userLocationInformationNr.tAI = new NGAP_TAI();
        userLocationInformationNr.tAI.tAC = new NGAP_TAC(tai.tac.toByteArray());
        userLocationInformationNr.tAI.pLMNIdentity = NgapUtils.plmnEncode(tai.plmn);
        userLocationInformationNr.timeStamp = new NGAP_TimeStamp(new Octet4(TimeStamp.getCurrentTime().getSeconds()).toByteArray());
        return userLocationInformationNr;
    }

    public static NGAP_GlobalRANNodeID createGlobalGnbId(int globalGnbId, VPlmn gnbPlmn) {
        var res = new NGAP_GlobalGNB_ID();
        res.pLMNIdentity = NgapUtils.plmnEncode(gnbPlmn);
        res.gNB_ID = new NGAP_GNB_ID();
        res.gNB_ID.gNB_ID = new NGAP_BitString(new Octet4(globalGnbId).toByteArray(true), 32);

        var ret = new NGAP_GlobalRANNodeID();
        ret.globalGNB_ID = res;
        return ret;
    }

    private static NGAP_SliceSupportList createSliceSupportList(IESNssai[] taiSliceSupportNssais) {
        var res = new NGAP_SliceSupportList();

        if (taiSliceSupportNssais != null) {
            for (var nssai : taiSliceSupportNssais) {
                var item = new NGAP_SliceSupportItem();
                item.s_NSSAI = new NGAP_S_NSSAI();
                if (nssai.sd != null) {
                    item.s_NSSAI.sD = new NGAP_SD(nssai.sd.value.toByteArray());
                }
                if (nssai.sst != null) {
                    item.s_NSSAI.sST = new NGAP_SST(nssai.sst.value.toByteArray());
                }
                res.list.add(item);
            }
        }

        return res;
    }

    private static NGAP_BroadcastPLMNList createBroadcastPlmnList(SupportedTA.BroadcastPlmn[] broadcastPlmns) {
        var res = new NGAP_BroadcastPLMNList();
        for (var broadcastPlmn : broadcastPlmns) {
            var item = new NGAP_BroadcastPLMNItem();
            item.pLMNIdentity = NgapUtils.plmnEncode(broadcastPlmn.plmn);
            item.tAISliceSupportList = createSliceSupportList(broadcastPlmn.taiSliceSupportNssais);
            res.list.add(item);
        }
        return res;
    }

    public static NGAP_SupportedTAList createSupportedTAList(SupportedTA[] supportedTAs) {
        var res = new NGAP_SupportedTAList();
        for (var supportedTa : supportedTAs) {
            var supportedTaiItem = new NGAP_SupportedTAItem();
            supportedTaiItem.tAC = new NGAP_TAC(supportedTa.tac.toByteArray());
            supportedTaiItem.broadcastPLMNList = createBroadcastPlmnList(supportedTa.broadcastPlmns);
            res.list.add(supportedTaiItem);
        }
        return res;
    }
}
