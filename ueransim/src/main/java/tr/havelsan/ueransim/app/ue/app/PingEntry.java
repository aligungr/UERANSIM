/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.app;

class PingEntry {
    public final long timestamp; // TODO: Use packet receive time instead since UeAppTask thread may add some overhead
    public final String name;
    public final String address;
    public final int timeoutSec;

    public PingEntry(long timestamp, String name, String address, int timeoutSec) {
        this.timestamp = timestamp;
        this.name = name;
        this.address = address;
        this.timeoutSec = timeoutSec;
    }

    public String getAddressDisplay() {
        if (name.equals(address))
            return name;
        return name + " (" + address + ")";
    }
}
