package tr.havelsan.ueransim.events.ue;

import tr.havelsan.ueransim.api.nas.NasTimer;

public class UeTimerExpireEvent extends UeEvent {

    public final NasTimer timer;

    public UeTimerExpireEvent(NasTimer timer) {
        this.timer = timer;
    }

    @Override
    public String toString() {
        return "UeTimerExpireEvent{" +
                "timer=" + timer +
                '}';
    }
}
