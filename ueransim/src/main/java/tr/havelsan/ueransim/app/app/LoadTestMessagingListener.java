package tr.havelsan.ueransim.app.app;

import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.nas.impl.messages.*;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupFailure;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupRequest;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupResponse;
import tr.havelsan.ueransim.utils.console.BaseConsole;

import java.util.HashMap;
import java.util.Map;

class LoadTestMessagingListener implements INodeMessagingListener {
    private final BaseConsole console;

    private final Map<Integer, Long> ngSetupTimers = new HashMap<>();
    private final Map<String, Long> registrationTimers = new HashMap<>();
    private final Map<String, Long> authenticationTimers = new HashMap<>();
    private final Map<String, Long> securityModeControlTimers = new HashMap<>();
    private final Map<String, Long> phase1Timers = new HashMap<>();
    private final Map<String, Long> phase2Timers = new HashMap<>();
    private final Map<String, Long> phase3Timers = new HashMap<>();
    private final Map<String, Long> deregistrationTimers = new HashMap<>();

    public LoadTestMessagingListener(BaseConsole console) {
        this.console = console;
    }

    @Override
    public void onSend(BaseSimContext ctx, Object message) {
        if (message instanceof NGAP_NGSetupRequest) {
            int gnbId = ((GnbSimContext) ctx).config.gnbId;
            ngSetupTimers.put(gnbId, System.currentTimeMillis());
        } else if (message instanceof RegistrationRequest) {
            String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
            registrationTimers.put(supi, System.currentTimeMillis());
            phase1Timers.put(supi, System.currentTimeMillis());
        } else if (message instanceof AuthenticationResponse) {
            String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
            phase2Timers.put(supi, System.currentTimeMillis());

            long delta = System.currentTimeMillis() - authenticationTimers.get(supi);
            console.println(null, "\u2714 [Authentication (UE/RAN)] [ue: %s] [%d ms]", supi, delta);
        } else if (message instanceof SecurityModeComplete) {
            String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
            phase3Timers.put(supi, System.currentTimeMillis());

            long delta = System.currentTimeMillis() - securityModeControlTimers.get(supi);
            console.println(null, "\u2714 [Security Mode Control (UE/RAN)] [ue: %s] [%d ms]", supi, delta);
        } else if (message instanceof DeRegistrationRequestUeOriginating) {
            String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
            deregistrationTimers.put(supi, System.currentTimeMillis());
        }
    }

    @Override
    public void onReceive(BaseSimContext ctx, Object message) {
        if (message instanceof NGAP_NGSetupFailure) {
            int gnbId = ((GnbSimContext) ctx).config.gnbId;
            long delta = System.currentTimeMillis() - ngSetupTimers.get(gnbId);
            console.println(null, "\u2718 [NGSetup] [gnbId: %d] [%d ms]", gnbId, delta);
        } else if (message instanceof NGAP_NGSetupResponse) {
            int gnbId = ((GnbSimContext) ctx).config.gnbId;
            long delta = System.currentTimeMillis() - ngSetupTimers.get(gnbId);
            console.println(null, "\u2714 [NGSetup] [gnbId: %d] [%d ms]", gnbId, delta);
        } else if (message instanceof RegistrationReject) {
            String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
            long delta = System.currentTimeMillis() - registrationTimers.get(supi);
            console.println(null, "\u2718 [Registration] [ue: %s] [%d ms]", supi, delta);
        } else if (message instanceof RegistrationAccept) {
            String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
            long delta = System.currentTimeMillis() - registrationTimers.get(supi);
            console.println(null, "\u2714 [Registration] [ue: %s] [%d ms]", supi, delta);

            long delta2 = System.currentTimeMillis() - phase3Timers.get(supi);
            console.println(null, "\u2714 [Phase 3 (Network)] [SecurityModeControl-RegistrationAccept] [ue: %s] [%d ms]", supi, delta2);
        } else if (message instanceof AuthenticationRequest) {
            String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
            authenticationTimers.put(supi, System.currentTimeMillis());

            long delta = System.currentTimeMillis() - phase1Timers.get(supi);
            console.println(null, "\u2714 [Phase 1 (Network)] [Registration-Authentication] [ue: %s] [%d ms]", supi, delta);
        } else if (message instanceof SecurityModeCommand) {
            String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
            securityModeControlTimers.put(supi, System.currentTimeMillis());

            long delta = System.currentTimeMillis() - phase2Timers.get(supi);
            console.println(null, "\u2714 [Phase 2 (Network)] [Authentication-SecurityModeControl] [ue: %s] [%d ms]", supi, delta);
        } else if (message instanceof DeRegistrationAcceptUeOriginating) {
            String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
            long delta = System.currentTimeMillis() - deregistrationTimers.get(supi);
            console.println(null, "\u2714 [De-Registration] [ue: %s] [%d ms]", supi, delta);
        }
    }
}