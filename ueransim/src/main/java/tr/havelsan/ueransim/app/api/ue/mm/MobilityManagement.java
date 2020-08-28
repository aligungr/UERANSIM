/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package tr.havelsan.ueransim.app.api.ue.mm;

import tr.havelsan.ueransim.app.api.nas.NasTimer;
import tr.havelsan.ueransim.app.api.ue.UserEquipment;
import tr.havelsan.ueransim.app.core.UeSimContext;
import tr.havelsan.ueransim.app.testing.TestCommand;
import tr.havelsan.ueransim.app.testing.TestCommand_Deregistration;
import tr.havelsan.ueransim.app.testing.TestCommand_InitialRegistration;
import tr.havelsan.ueransim.app.testing.TestCommand_PeriodicRegistration;
import tr.havelsan.ueransim.app.utils.Debugging;
import tr.havelsan.ueransim.nas.core.messages.PlainMmMessage;
import tr.havelsan.ueransim.nas.impl.enums.ERegistrationType;
import tr.havelsan.ueransim.nas.impl.ies.IEDeRegistrationType;
import tr.havelsan.ueransim.nas.impl.messages.*;
import tr.havelsan.ueransim.utils.console.Logging;
import tr.havelsan.ueransim.utils.Tag;

public class MobilityManagement {

    public static void sendMm(UeSimContext ctx, PlainMmMessage message) {
        Debugging.assertThread(ctx);

        UserEquipment.sendNas(ctx, message);
    }

    public static void receiveMm(UeSimContext ctx, PlainMmMessage message) {
        Debugging.assertThread(ctx);

        if (message instanceof AuthenticationRequest) {
            MmAuthentication.receiveAuthenticationRequest(ctx, (AuthenticationRequest) message);
        } else if (message instanceof AuthenticationResult) {
            MmAuthentication.receiveAuthenticationResult(ctx, (AuthenticationResult) message);
        } else if (message instanceof AuthenticationResponse) {
            MmAuthentication.receiveAuthenticationResponse(ctx, (AuthenticationResponse) message);
        } else if (message instanceof AuthenticationReject) {
            MmAuthentication.receiveAuthenticationReject(ctx, (AuthenticationReject) message);
        } else if (message instanceof RegistrationReject) {
            MmRegistration.receiveRegistrationReject(ctx, (RegistrationReject) message);
        } else if (message instanceof IdentityRequest) {
            MmIdentity.receiveIdentityRequest(ctx, (IdentityRequest) message);
        } else if (message instanceof RegistrationAccept) {
            MmRegistration.receiveRegistrationAccept(ctx, (RegistrationAccept) message);
        } else if (message instanceof ServiceAccept) {
            MmService.receiveServiceAccept(ctx, (ServiceAccept) message);
        } else if (message instanceof ServiceReject) {
            MmService.receiveServiceReject(ctx, (ServiceReject) message);
        } else if (message instanceof SecurityModeCommand) {
            MmSecurity.receiveSecurityModeCommand(ctx, (SecurityModeCommand) message);
        } else if (message instanceof ConfigurationUpdateCommand) {
            MmConfiguration.receiveConfigurationUpdate(ctx, (ConfigurationUpdateCommand) message);
        } else if (message instanceof DeRegistrationAcceptUeOriginating) {
            MmDeregistration.receiveDeregistrationAccept(ctx, (DeRegistrationAcceptUeOriginating) message);
        } else if (message instanceof DeRegistrationRequestUeTerminated) {
            MmDeregistration.receiveDeregistrationRequest(ctx, (DeRegistrationRequestUeTerminated) message);
        } else {
            Logging.error(Tag.MESSAGING, "Unhandled message received: %s", message.getClass().getSimpleName());
        }
    }

    public static void receiveTimerExpire(UeSimContext ctx, NasTimer timer) {
        Debugging.assertThread(ctx);

        if (timer.timerCode == 3512) {
            MmRegistration.sendRegistration(ctx, ERegistrationType.PERIODIC_REGISTRATION_UPDATING);
        }
    }

    public static boolean executeCommand(UeSimContext ctx, TestCommand cmd) {
        Debugging.assertThread(ctx);

        if (cmd instanceof TestCommand_InitialRegistration) {
            MmRegistration.sendRegistration(ctx, ERegistrationType.INITIAL_REGISTRATION);
            return true;
        } else if (cmd instanceof TestCommand_PeriodicRegistration) {
            MmRegistration.sendRegistration(ctx, ERegistrationType.PERIODIC_REGISTRATION_UPDATING);
            return true;
        } else if (cmd instanceof TestCommand_Deregistration) {
            // todo: switch off
            MmDeregistration.sendDeregistration(ctx, IEDeRegistrationType.ESwitchOff.NORMAL_DE_REGISTRATION);
            return true;
        }

        return false;
    }
}
