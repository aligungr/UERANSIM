/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.mm;

import tr.havelsan.ueransim.app.common.enums.EConnType;
import tr.havelsan.ueransim.app.common.enums.EMmState;
import tr.havelsan.ueransim.app.common.enums.EMmSubState;
import tr.havelsan.ueransim.app.common.enums.ERmState;
import tr.havelsan.ueransim.app.common.itms.IwPlmnSearchRequest;
import tr.havelsan.ueransim.app.common.itms.IwPlmnSearchResponse;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.common.testcmd.TestCmd;
import tr.havelsan.ueransim.app.common.testcmd.TestCmd_Deregistration;
import tr.havelsan.ueransim.app.common.testcmd.TestCmd_InitialRegistration;
import tr.havelsan.ueransim.app.common.testcmd.TestCmd_PeriodicRegistration;
import tr.havelsan.ueransim.app.ue.UeNode;
import tr.havelsan.ueransim.app.ue.nas.NasTimer;
import tr.havelsan.ueransim.app.ue.nas.NasTransport;
import tr.havelsan.ueransim.app.ue.sm.SessionManagement;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.nas.core.messages.PlainMmMessage;
import tr.havelsan.ueransim.nas.impl.enums.EFollowOnRequest;
import tr.havelsan.ueransim.nas.impl.enums.ERegistrationType;
import tr.havelsan.ueransim.nas.impl.ies.IEDeRegistrationType;
import tr.havelsan.ueransim.nas.impl.messages.*;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.exceptions.NotImplementedException;


public class MobilityManagement {

    public static void sendMm(UeSimContext ctx, PlainMmMessage message) {
        NasTransport.sendNas(ctx, message);
    }

    public static void receiveMm(UeSimContext ctx, PlainMmMessage message) {
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
        } else if (message instanceof DlNasTransport) {
            SessionManagement.receiveDl(ctx, (DlNasTransport) message);
        } else {
            Log.error(Tag.MSG, "Unhandled message received: %s", message.getClass().getSimpleName());
        }
    }

    public static void receiveTimerExpire(UeSimContext ctx, NasTimer timer) {
        if (timer.timerCode == 3512) {
            if (UeNode.AUTO && ctx.mmCtx.mmState == EMmState.MM_REGISTERED) {
                MmRegistration.sendRegistration(ctx, ERegistrationType.PERIODIC_REGISTRATION_UPDATING, EFollowOnRequest.NO_FOR_PENDING);
            }
        }

        if (timer.timerCode == 3346) {
            if (UeNode.AUTO && ctx.mmCtx.mmSubState == EMmSubState.MM_DEREGISTERED__NORMAL_SERVICE) {
                MmRegistration.sendRegistration(ctx, ERegistrationType.INITIAL_REGISTRATION, EFollowOnRequest.NO_FOR_PENDING);
            }
        }
    }

    public static boolean executeCommand(UeSimContext ctx, TestCmd cmd) {
        if (cmd instanceof TestCmd_InitialRegistration) {
            MmRegistration.sendRegistration(ctx, ERegistrationType.INITIAL_REGISTRATION, ((TestCmd_InitialRegistration) cmd).followOn);
            return true;
        } else if (cmd instanceof TestCmd_PeriodicRegistration) {
            MmRegistration.sendRegistration(ctx, ERegistrationType.PERIODIC_REGISTRATION_UPDATING, ((TestCmd_PeriodicRegistration) cmd).followOn);
            return true;
        } else if (cmd instanceof TestCmd_Deregistration) {
            MmDeregistration.sendDeregistration(ctx, ((TestCmd_Deregistration) cmd).isSwitchOff
                    ? IEDeRegistrationType.ESwitchOff.SWITCH_OFF : IEDeRegistrationType.ESwitchOff.NORMAL_DE_REGISTRATION);
            return true;
        }

        return false;
    }

    public static void switchState(UeSimContext ctx, EMmState state, EMmSubState subState) {
        ctx.mmCtx.mmState = state;
        ctx.mmCtx.mmSubState = subState;

        ctx.sim.triggerOnSwitch(ctx, state);
        ctx.sim.triggerOnSwitch(ctx, subState);

        Log.info(Tag.STATE, "UE switches to state: %s/%s", state, subState);
    }

    public static void switchState(UeSimContext ctx, ERmState state) {
        ctx.mmCtx.rmState = state;
        ctx.sim.triggerOnSwitch(ctx, state);
        Log.info(Tag.STATE, "UE switches to state: %s", state);
    }

    public static void cycle(UeSimContext ctx) {
        if (ctx.mmCtx.mmState == EMmState.MM_NULL) {
            switchState(ctx, EMmState.MM_DEREGISTERED, EMmSubState.MM_DEREGISTERED__PLMN_SEARCH);
            return;
        }

        if (ctx.mmCtx.mmSubState == EMmSubState.MM_DEREGISTERED__PLMN_SEARCH) {
            long current = System.currentTimeMillis();
            if (ctx.mmCtx.lastPlmnSearchTrigger == 0) {
                ctx.mmCtx.lastPlmnSearchTrigger = current;
                return;
            }
            var elapsedMs = current - ctx.mmCtx.lastPlmnSearchTrigger;
            if (elapsedMs > 1000) {
                ctx.nts.findTask(ItmsId.UE_TASK_MR).push(new IwPlmnSearchRequest());
                ctx.mmCtx.lastPlmnSearchTrigger = current;
            }
            return;
        }

        if (ctx.mmCtx.mmSubState == EMmSubState.MM_DEREGISTERED__NORMAL_SERVICE) {
            if (UeNode.AUTO && !ctx.ueTimers.t3346.isRunning()) {
                MmRegistration.sendRegistration(ctx, ERegistrationType.INITIAL_REGISTRATION, EFollowOnRequest.NO_FOR_PENDING);
            }
            return;
        }

        if (ctx.mmCtx.mmState == EMmState.MM_REGISTERED_INITIATED) {
            return;
        }

        if (ctx.mmCtx.mmSubState == EMmSubState.MM_REGISTERED__NORMAL_SERVICE) {
            return;
        }

        if (ctx.mmCtx.mmState == EMmState.MM_DEREGISTERED_INITIATED) {
            return;
        }

        if (ctx.mmCtx.mmSubState == EMmSubState.MM_DEREGISTERED__NA) {
            return;
        }

        if (UeNode.AUTO) {
            throw new NotImplementedException("unhandled UE MM state");
        }
    }

    public static void receiveItmsMessage(UeSimContext ctx, Object msg) {
        if (msg instanceof IwPlmnSearchResponse) {
            ctx.connectedGnb = ((IwPlmnSearchResponse) msg).gnbId;
            ctx.sim.triggerOnConnected(ctx, EConnType.UE_MR_GNB);
            Log.info(Tag.FLOW, "UE connected to gNB.");

            switchState(ctx, EMmState.MM_DEREGISTERED, EMmSubState.MM_DEREGISTERED__NORMAL_SERVICE);
        }
    }
}
