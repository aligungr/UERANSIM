/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.mm;

import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.nas.eap.Eap;
import tr.havelsan.ueransim.nas.impl.messages.ServiceAccept;
import tr.havelsan.ueransim.nas.impl.messages.ServiceReject;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;

public class MmService {

    public static void receiveServiceAccept(UeSimContext ctx, ServiceAccept message) {
        Log.funcIn("Handling: Service Accept");

        if (message.eapMessage != null) {
            if (message.eapMessage.eap.code.equals(Eap.ECode.FAILURE)) {
                MmAuthentication.receiveEapFailureMessage(ctx, message.eapMessage.eap);
            } else {
                Log.warning(Tag.FLOW, "network sent EAP with type of %s in ServiceAccept, ignoring EAP IE.",
                        message.eapMessage.eap.code.name());
            }
        }

        Log.funcOut();
    }

    public static void receiveServiceReject(UeSimContext ctx, ServiceReject message) {
        Log.funcIn("Handling: Service Accept");

        if (message.eapMessage != null) {
            if (message.eapMessage.eap.code.equals(Eap.ECode.FAILURE)) {
                MmAuthentication.receiveEapFailureMessage(ctx, message.eapMessage.eap);
            } else {
                Log.warning(Tag.FLOW, "network sent EAP with type of %s in ServiceReject, ignoring EAP IE.",
                        message.eapMessage.eap.code.name());
            }
        }

        Log.funcOut();
    }
}
