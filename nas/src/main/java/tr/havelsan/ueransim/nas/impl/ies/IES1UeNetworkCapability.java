/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.bits.Bit;

public class IES1UeNetworkCapability extends InformationElement4 {
    public static final String NAME_EEA7 = "EEA7";
    public static final String NAME_EEA6 = "EEA6";
    public static final String NAME_EEA5 = "EEA5";
    public static final String NAME_EEA4 = "EEA4";
    public static final String NAME_128_EEA3 = "128-EEA3";
    public static final String NAME_128_EEA2 = "128-EEA2";
    public static final String NAME_128_EEA1 = "128-EEA1";
    public static final String NAME_EEA0 = "EEA0";
    public static final String NAME_EIA7 = "EIA7";
    public static final String NAME_EIA6 = "EIA6";
    public static final String NAME_EIA5 = "EIA5";
    public static final String NAME_EIA4 = "EIA4";
    public static final String NAME_128_EIA3 = "128-EIA3";
    public static final String NAME_128_EIA2 = "128-EIA2";
    public static final String NAME_128_EIA1 = "128-EIA1";
    public static final String NAME_EIA0 = "EIA0";
    public static final String NAME_UEA7 = "UEA7";
    public static final String NAME_UEA6 = "UEA6";
    public static final String NAME_UEA5 = "UEA5";
    public static final String NAME_UEA4 = "UEA4";
    public static final String NAME_UEA3 = "UEA3";
    public static final String NAME_UEA2 = "UEA2";
    public static final String NAME_UEA1 = "UEA1";
    public static final String NAME_UEA0 = "UEA0";
    public static final String NAME_UIA7 = "UIA7";
    public static final String NAME_UIA6 = "UIA6";
    public static final String NAME_UIA5 = "UIA5";
    public static final String NAME_UIA4 = "UIA4";
    public static final String NAME_UIA3 = "UIA3";
    public static final String NAME_UIA2 = "UIA2";
    public static final String NAME_UIA1 = "UIA1";
    public static final String NAME_UCS2 = "UCS2";
    public static final String NAME_NF = "NF";
    public static final String NAME_1xSR_VCC = "1xSR VCC";
    public static final String NAME_LCS = "LCS";
    public static final String NAME_LPP = "LPP";
    public static final String NAME_ACC_CSFB = "ACC-CSFB";
    public static final String NAME_H_245_ASH = "H.245-ASH";
    public static final String NAME_ProSe = "ProSe";
    public static final String NAME_ProSe_dd = "ProSe-dd";
    public static final String NAME_ProSe_dc = "ProSe-dc";
    public static final String NAME_Prose_relay = "Prose-relay";
    public static final String NAME_CP_CIoT = "CP CIoT";
    public static final String NAME_UP_CIoT = "UP CIoT";
    public static final String NAME_S1_U_data = "S1-U data";
    public static final String NAME_ERw_oPDN = "ERw/oPDN";
    public static final String NAME_HC_CP_CIoT = "HC-CP CIoT";
    public static final String NAME_ePCO = "ePCO";
    public static final String NAME_multipleDRB = "multipleDRB";
    public static final String NAME_V2X_PC5 = "V2X PC5";
    public static final String NAME_RestrictEC = "RestrictEC";
    public static final String NAME_CP_backoff = "CP backoff";
    public static final String NAME_DCNR = "DCNR";
    public static final String NAME_N1mode = "N1mode";
    public static final String NAME_SGC = "SGC";

    public Bit supported_EEA7;
    public Bit supported_EEA6;
    public Bit supported_EEA5;
    public Bit supported_EEA4;
    public Bit supported_128_EEA3;
    public Bit supported_128_EEA2;
    public Bit supported_128_EEA1;
    public Bit supported_EEA0;

    public Bit supported_EIA7;
    public Bit supported_EIA6;
    public Bit supported_EIA5;
    public Bit supported_EIA4;
    public Bit supported_128_EIA3;
    public Bit supported_128_EIA2;
    public Bit supported_128_EIA1;
    public Bit supported_EIA0;

    public Bit supported_UEA7;
    public Bit supported_UEA6;
    public Bit supported_UEA5;
    public Bit supported_UEA4;
    public Bit supported_UEA3;
    public Bit supported_UEA2;
    public Bit supported_UEA1;
    public Bit supported_UEA0;

    public Bit supported_UIA7;
    public Bit supported_UIA6;
    public Bit supported_UIA5;
    public Bit supported_UIA4;
    public Bit supported_UIA3;
    public Bit supported_UIA2;
    public Bit supported_UIA1;
    public Bit supported_UCS2;

    public Bit supported_NF;
    public Bit supported_1xSR_VCC;
    public Bit supported_LCS;
    public Bit supported_LPP;
    public Bit supported_ACC_CSFB;
    public Bit supported_H_245_ASH;
    public Bit supported_ProSe;
    public Bit supported_ProSe_dd;

    public Bit supported_ProSe_dc;
    public Bit supported_Prose_relay;
    public Bit supported_CP_CIoT;
    public Bit supported_UP_CIoT;
    public Bit supported_S1_U_data;
    public Bit supported_ERw_oPDN;
    public Bit supported_HC_CP_CIoT;
    public Bit supported_ePCO;

    public Bit supported_multipleDRB;
    public Bit supported_V2X_PC5;
    public Bit supported_RestrictEC;
    public Bit supported_CP_backoff;
    public Bit supported_DCNR;
    public Bit supported_N1mode;
    public Bit supported_SGC;

    public IES1UeNetworkCapability() {
    }

    public IES1UeNetworkCapability(Bit supported_EEA7, Bit supported_EEA6, Bit supported_EEA5, Bit supported_EEA4, Bit supported_128_EEA3, Bit supported_128_EEA2, Bit supported_128_EEA1, Bit supported_EEA0, Bit supported_EIA7, Bit supported_EIA6, Bit supported_EIA5, Bit supported_EIA4, Bit supported_128_EIA3, Bit supported_128_EIA2, Bit supported_128_EIA1, Bit supported_EIA0, Bit supported_UEA7, Bit supported_UEA6, Bit supported_UEA5, Bit supported_UEA4, Bit supported_UEA3, Bit supported_UEA2, Bit supported_UEA1, Bit supported_UEA0, Bit supported_UIA7, Bit supported_UIA6, Bit supported_UIA5, Bit supported_UIA4, Bit supported_UIA3, Bit supported_UIA2, Bit supported_UIA1, Bit supported_UCS2, Bit supported_NF, Bit supported_1xSR_VCC, Bit supported_LCS, Bit supported_LPP, Bit supported_ACC_CSFB, Bit supported_H_245_ASH, Bit supported_ProSe, Bit supported_ProSe_dd, Bit supported_ProSe_dc, Bit supported_Prose_relay, Bit supported_CP_CIoT, Bit supported_UP_CIoT, Bit supported_S1_U_data, Bit supported_ERw_oPDN, Bit supported_HC_CP_CIoT, Bit supported_ePCO, Bit supported_multipleDRB, Bit supported_V2X_PC5, Bit supported_RestrictEC, Bit supported_CP_backoff, Bit supported_DCNR, Bit supported_N1mode, Bit supported_SGC) {
        this.supported_EEA7 = supported_EEA7;
        this.supported_EEA6 = supported_EEA6;
        this.supported_EEA5 = supported_EEA5;
        this.supported_EEA4 = supported_EEA4;
        this.supported_128_EEA3 = supported_128_EEA3;
        this.supported_128_EEA2 = supported_128_EEA2;
        this.supported_128_EEA1 = supported_128_EEA1;
        this.supported_EEA0 = supported_EEA0;
        this.supported_EIA7 = supported_EIA7;
        this.supported_EIA6 = supported_EIA6;
        this.supported_EIA5 = supported_EIA5;
        this.supported_EIA4 = supported_EIA4;
        this.supported_128_EIA3 = supported_128_EIA3;
        this.supported_128_EIA2 = supported_128_EIA2;
        this.supported_128_EIA1 = supported_128_EIA1;
        this.supported_EIA0 = supported_EIA0;
        this.supported_UEA7 = supported_UEA7;
        this.supported_UEA6 = supported_UEA6;
        this.supported_UEA5 = supported_UEA5;
        this.supported_UEA4 = supported_UEA4;
        this.supported_UEA3 = supported_UEA3;
        this.supported_UEA2 = supported_UEA2;
        this.supported_UEA1 = supported_UEA1;
        this.supported_UEA0 = supported_UEA0;
        this.supported_UIA7 = supported_UIA7;
        this.supported_UIA6 = supported_UIA6;
        this.supported_UIA5 = supported_UIA5;
        this.supported_UIA4 = supported_UIA4;
        this.supported_UIA3 = supported_UIA3;
        this.supported_UIA2 = supported_UIA2;
        this.supported_UIA1 = supported_UIA1;
        this.supported_UCS2 = supported_UCS2;
        this.supported_NF = supported_NF;
        this.supported_1xSR_VCC = supported_1xSR_VCC;
        this.supported_LCS = supported_LCS;
        this.supported_LPP = supported_LPP;
        this.supported_ACC_CSFB = supported_ACC_CSFB;
        this.supported_H_245_ASH = supported_H_245_ASH;
        this.supported_ProSe = supported_ProSe;
        this.supported_ProSe_dd = supported_ProSe_dd;
        this.supported_ProSe_dc = supported_ProSe_dc;
        this.supported_Prose_relay = supported_Prose_relay;
        this.supported_CP_CIoT = supported_CP_CIoT;
        this.supported_UP_CIoT = supported_UP_CIoT;
        this.supported_S1_U_data = supported_S1_U_data;
        this.supported_ERw_oPDN = supported_ERw_oPDN;
        this.supported_HC_CP_CIoT = supported_HC_CP_CIoT;
        this.supported_ePCO = supported_ePCO;
        this.supported_multipleDRB = supported_multipleDRB;
        this.supported_V2X_PC5 = supported_V2X_PC5;
        this.supported_RestrictEC = supported_RestrictEC;
        this.supported_CP_backoff = supported_CP_backoff;
        this.supported_DCNR = supported_DCNR;
        this.supported_N1mode = supported_N1mode;
        this.supported_SGC = supported_SGC;
    }

    @Override
    protected IES1UeNetworkCapability decodeIE4(OctetInputStream stream, int length) {
        var cap = new IES1UeNetworkCapability();

        if (length >= 1) {
            var bits = stream.readOctet();
            cap.supported_EEA7 = bits.getBit(0);
            cap.supported_EEA6 = bits.getBit(1);
            cap.supported_EEA5 = bits.getBit(2);
            cap.supported_EEA4 = bits.getBit(3);
            cap.supported_128_EEA3 = bits.getBit(4);
            cap.supported_128_EEA2 = bits.getBit(5);
            cap.supported_128_EEA1 = bits.getBit(6);
            cap.supported_EEA0 = bits.getBit(7);
        }

        if (length >= 2) {
            var bits = stream.readOctet();
            cap.supported_EIA7 = bits.getBit(0);
            cap.supported_EIA6 = bits.getBit(1);
            cap.supported_EIA5 = bits.getBit(2);
            cap.supported_EIA4 = bits.getBit(3);
            cap.supported_128_EIA3 = bits.getBit(4);
            cap.supported_128_EIA2 = bits.getBit(5);
            cap.supported_128_EIA1 = bits.getBit(6);
            cap.supported_EIA0 = bits.getBit(7);
        }

        if (length >= 3) {
            var bits = stream.readOctet();
            cap.supported_UEA7 = bits.getBit(0);
            cap.supported_UEA6 = bits.getBit(1);
            cap.supported_UEA5 = bits.getBit(2);
            cap.supported_UEA4 = bits.getBit(3);
            cap.supported_UEA3 = bits.getBit(4);
            cap.supported_UEA2 = bits.getBit(5);
            cap.supported_UEA1 = bits.getBit(6);
            cap.supported_UEA0 = bits.getBit(7);
        }

        if (length >= 4) {
            var bits = stream.readOctet();
            cap.supported_UIA7 = bits.getBit(0);
            cap.supported_UIA6 = bits.getBit(1);
            cap.supported_UIA5 = bits.getBit(2);
            cap.supported_UIA4 = bits.getBit(3);
            cap.supported_UIA3 = bits.getBit(4);
            cap.supported_UIA2 = bits.getBit(5);
            cap.supported_UIA1 = bits.getBit(6);
            cap.supported_UCS2 = bits.getBit(7);
        }

        if (length >= 5) {
            var bits = stream.readOctet();
            cap.supported_NF = bits.getBit(0);
            cap.supported_1xSR_VCC = bits.getBit(1);
            cap.supported_LCS = bits.getBit(2);
            cap.supported_LPP = bits.getBit(3);
            cap.supported_ACC_CSFB = bits.getBit(4);
            cap.supported_H_245_ASH = bits.getBit(5);
            cap.supported_ProSe = bits.getBit(6);
            cap.supported_ProSe_dd = bits.getBit(7);
        }

        if (length >= 6) {
            var bits = stream.readOctet();
            cap.supported_ProSe_dc = bits.getBit(0);
            cap.supported_Prose_relay = bits.getBit(1);
            cap.supported_CP_CIoT = bits.getBit(2);
            cap.supported_UP_CIoT = bits.getBit(3);
            cap.supported_S1_U_data = bits.getBit(4);
            cap.supported_ERw_oPDN = bits.getBit(5);
            cap.supported_HC_CP_CIoT = bits.getBit(6);
            cap.supported_ePCO = bits.getBit(7);
        }

        if (length >= 7) {
            var bits = stream.readOctet();
            cap.supported_multipleDRB = bits.getBit(0);
            cap.supported_V2X_PC5 = bits.getBit(1);
            cap.supported_RestrictEC = bits.getBit(2);
            cap.supported_CP_backoff = bits.getBit(3);
            cap.supported_DCNR = bits.getBit(4);
            cap.supported_N1mode = bits.getBit(5);
            cap.supported_SGC = bits.getBit(6);
        }

        // Other octets are spare, if any
        if (length >= 8) {
            stream.readOctetString(length - 7);
        }

        return cap;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        final var bits = new Bit[][]{
                {
                        supported_EEA0,
                        supported_128_EEA1,
                        supported_128_EEA2,
                        supported_128_EEA3,
                        supported_EEA4,
                        supported_EEA5,
                        supported_EEA6,
                        supported_EEA7,
                },
                {
                        supported_EIA0,
                        supported_128_EIA1,
                        supported_128_EIA2,
                        supported_128_EIA3,
                        supported_EIA4,
                        supported_EIA5,
                        supported_EIA6,
                        supported_EIA7,
                },
                {
                        supported_UEA0,
                        supported_UEA1,
                        supported_UEA2,
                        supported_UEA3,
                        supported_UEA4,
                        supported_UEA5,
                        supported_UEA6,
                        supported_UEA7,
                },
                {
                        supported_UCS2,
                        supported_UIA1,
                        supported_UIA2,
                        supported_UIA3,
                        supported_UIA4,
                        supported_UIA5,
                        supported_UIA6,
                        supported_UIA7,
                },
                {
                        supported_ProSe_dd,
                        supported_ProSe,
                        supported_H_245_ASH,
                        supported_ACC_CSFB,
                        supported_LPP,
                        supported_LCS,
                        supported_1xSR_VCC,
                        supported_NF,
                },
                {
                        supported_ePCO,
                        supported_HC_CP_CIoT,
                        supported_ERw_oPDN,
                        supported_S1_U_data,
                        supported_UP_CIoT,
                        supported_CP_CIoT,
                        supported_Prose_relay,
                        supported_ProSe_dc,
                },
                {
                        supported_SGC,
                        supported_N1mode,
                        supported_DCNR,
                        supported_CP_backoff,
                        supported_RestrictEC,
                        supported_V2X_PC5,
                        supported_multipleDRB,
                },
        };

        stream.writeOctets(Utils.fixedBitsToOctetArray(bits));
    }
}
