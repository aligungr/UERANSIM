package com.runsim.backend.machine;

import com.runsim.backend.machine.handleresult.MessageHandleResult;
import com.runsim.backend.machine.handleresult.SendData;
import com.runsim.backend.machine.stateresult.RedirectToState;
import com.runsim.backend.machine.stateresult.SendMessage;
import com.runsim.backend.machine.stateresult.StateResult;
import com.runsim.backend.machine.stateresult.SwitchToMachine;

import java.lang.reflect.Method;

public abstract class StateMachine {
    private String currentState = "initialState";

    public abstract StateResult initialState(byte[] input);

    public MessageHandleResult handleMessage(byte[] data) {
        while (true) {
            Method stateMethod;
            try {
                stateMethod = getClass().getDeclaredMethod(currentState, byte[].class);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }

            if (stateMethod.getReturnType() != StateResult.class)
                throw new RuntimeException("return type should be " + StateResult.class);

            StateResult stateResult;
            try {
                stateResult = (StateResult) stateMethod.invoke(this, (Object) data);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (stateResult instanceof SendMessage) {
                SendMessage res = (SendMessage) stateResult;
                this.currentState = res.getNextState();
                return new SendData(res.getData());
            }
            else if (stateResult instanceof RedirectToState) {
                RedirectToState res = (RedirectToState) stateResult;
                this.currentState = res.getRedirectState();
                //continue;
            }
            else if (stateResult instanceof SwitchToMachine) {
                SwitchToMachine res = (SwitchToMachine) stateResult;
                return new com.runsim.backend.machine.handleresult.SwitchToMachine(res.getStateMachine());
            }
            else {
                throw new RuntimeException("not handled state result");
            }
        }
    }
}
