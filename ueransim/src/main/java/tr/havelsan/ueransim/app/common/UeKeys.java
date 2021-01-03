/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.common;

import tr.havelsan.ueransim.utils.octets.OctetString;

public class UeKeys {
    public OctetString abba;

    public OctetString rand;
    public OctetString res;
    public OctetString resStar; // used in 5G-AKA

    public OctetString kAusf;
    public OctetString kSeaf;
    public OctetString kAmf;
    public OctetString kNasInt;
    public OctetString kNasEnc;

    public UeKeys deepCopy() {
        var keys = new UeKeys();
        keys.rand = this.rand;
        keys.res = this.res;
        keys.resStar = this.resStar;
        keys.kAusf = this.kAusf;
        keys.kSeaf = this.kSeaf;
        keys.kAmf = this.kAmf;
        keys.kNasInt = this.kNasInt;
        keys.kNasEnc = this.kNasEnc;
        return keys;
    }
}
