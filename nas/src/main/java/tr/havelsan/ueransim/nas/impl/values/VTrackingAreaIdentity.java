/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.values;

import tr.havelsan.ueransim.nas.core.NasValue;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.Octet3;

public class VTrackingAreaIdentity extends NasValue {
    public VPlmn plmn;
    public Octet3 tac;

    public VTrackingAreaIdentity() {
    }

    public VTrackingAreaIdentity(VPlmn plmn, Octet3 tac) {
        this.plmn = plmn;
        this.tac = tac;
    }

    public VTrackingAreaIdentity(VPlmn plmn, int tac) {
        this.plmn = plmn;
        this.tac = new Octet3(tac);
    }

    public VTrackingAreaIdentity(VPlmn plmn, String tac) {
        this.plmn = plmn;
        this.tac = new Octet3(tac);
    }

    public VTrackingAreaIdentity(int mcc, int mnc, int tac) {
        this.plmn = new VPlmn(mcc, mnc);
        this.tac = new Octet3(tac);
    }

    public VTrackingAreaIdentity(int mcc, int mnc, Octet3 tac) {
        this.plmn = new VPlmn(mcc, mnc);
        this.tac = tac;
    }

    public VTrackingAreaIdentity(int mcc, int mnc, String tac) {
        this.plmn = new VPlmn(mcc, mnc);
        this.tac = new Octet3(tac);
    }

    @Override
    public VTrackingAreaIdentity decode(OctetInputStream stream) {
        var res = new VTrackingAreaIdentity();
        res.plmn = new VPlmn().decode(stream);
        res.tac = stream.readOctet3();
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        plmn.encode(stream);
        stream.writeOctet3(tac);
    }
}
