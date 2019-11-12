package com.runsim.backend.nas.impl.values;

import com.runsim.backend.exceptions.EncodingException;
import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.nas.impl.enums.EMobileCountryCode;
import com.runsim.backend.nas.impl.enums.EMobileNetworkCode;
import com.runsim.backend.nas.impl.enums.EMobileNetworkCode2;
import com.runsim.backend.nas.impl.enums.EMobileNetworkCode3;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class VMccMnc extends NasValue {

    public EMobileCountryCode mcc;
    public EMobileNetworkCode mnc;

    @Override
    public NasValue decode(OctetInputStream stream) {
        var res = new VMccMnc();

        /* Decode MCC */
        int octet1 = stream.readOctetI();
        int mcc1 = octet1 & 0b1111;
        int mcc2 = (octet1 >> 4) & 0b1111;
        int octet2 = stream.readOctetI();
        int mcc3 = octet2 & 0b1111;
        int mcc = 100 * mcc1 + 10 * mcc2 + mcc3;
        res.mcc = EMobileCountryCode.fromValue(mcc);

        /* Decode MNC */
        int mnc3 = (octet2 >> 4) & 0b1111;
        int octet3 = stream.readOctetI();
        int mnc2 = octet3 & 0b1111;
        int mnc1 = (octet3 >> 4) & 0b1111;
        int mnc = 10 * mnc1 + mnc2;
        boolean longMnc = false;
        if ((mnc3 != 0xf) || (octet1 == 0xff && octet2 == 0xff && octet3 == 0xff)) {
            longMnc = true;
            mnc = 10 * mnc + mnc3;
        }
        if (longMnc) {
            res.mnc = EMobileNetworkCode3.fromValue(mcc * 1000 + mnc);
        } else {
            res.mnc = EMobileNetworkCode2.fromValue(mcc * 100 + mnc);
        }

        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        int mcc = this.mcc.value;
        int mcc3 = mcc % 10;
        int mcc2 = (mcc % 100) / 10;
        int mcc1 = (mcc % 1000) / 100;
        int octet1 = mcc2 << 4 | mcc1;

        boolean longMnc;
        int mnc;

        if (this.mnc == null)
            throw new EncodingException("mnc is null");
        if (this.mnc instanceof EMobileNetworkCode2) {
            longMnc = false;
            mnc = this.mcc.value % 100;
        } else {
            longMnc = true;
            mnc = this.mcc.value % 1000;
        }

        int mnc1 = longMnc ? (mnc % 1000) / 100 : (mnc % 100) / 10;
        int mnc2 = longMnc ? (mnc % 100) / 10 : (mnc % 10);
        int mnc3 = longMnc ? (mnc % 10) : 0xF;

        int octet2 = mnc3 << 4 | mcc3;
        int octet3 = mnc1 << 4 | mnc2;

        stream.writeOctet(octet1);
        stream.writeOctet(octet2);
        stream.writeOctet(octet3);
    }
}
