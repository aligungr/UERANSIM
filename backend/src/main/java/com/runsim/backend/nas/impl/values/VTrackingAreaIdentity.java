package com.runsim.backend.nas.impl.values;

import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet3;

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
