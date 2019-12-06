package com.runsim.backend.nas.eap;

import com.runsim.backend.utils.octets.OctetString;

public class EapNotification extends Eap {
    public OctetString rawData;

    public EapNotification() {
    }

    public EapNotification(OctetString rawData) {
        this.rawData = rawData;
    }
}
