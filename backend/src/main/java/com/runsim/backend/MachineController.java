package com.runsim.backend;

import com.runsim.backend.annotations.Starter;
import com.runsim.backend.annotations.State;
import com.runsim.backend.mts.MTSAdapter;
import com.runsim.backend.sctp.SCTPClient;
import com.sun.nio.sctp.MessageInfo;
import com.sun.nio.sctp.SctpChannel;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MachineController {
    private final Class machineType;
    private final Object machine;
    private final SCTPClient sctpClient;
    private Method starterMethod;
    private Map<String, Method> stateMethods;
    private Map<String, State> stateAnnotations;
    private String currentState;
    private boolean runCalled;
    private MachineContext machineContext;

    public MachineController(Class machineType, String host, int port) throws Exception {
        this.machineType = machineType;
        this.machine = machineType.getDeclaredConstructor().newInstance();
        this.sctpClient = new SCTPClient(host, port, Constants.NGAP_PROTOCOL_ID);
        this.currentState = null;
        this.starterMethod = null;
        this.machineContext = new MachineContext();
        this.stateMethods = new HashMap<>();
        this.stateAnnotations = new HashMap<>();

        for (var method : machine.getClass().getMethods()) {
            if (method.isAnnotationPresent(Starter.class)) {
                if (starterMethod != null)
                    throw new RuntimeException("multiple starters are not allowed");
                var params = method.getParameters();
                if (params.length != 1 || params[0].getType() != MachineContext.class)
                    throw new RuntimeException("bad parameters for starter method");
                if (!Action.class.isAssignableFrom(method.getReturnType()))
                    throw new RuntimeException("return type of the starter method must be Action");
                method.setAccessible(true);
                starterMethod = method;
            }
            if (method.isAnnotationPresent(State.class)) {
                var params = method.getParameters();
                if (params.length != 2 || params[1].getType() != MessageContext.class || params[0].getType() != MachineContext.class)
                    throw new RuntimeException("bad parameters for state function.");
                if (!Action.class.isAssignableFrom(method.getReturnType()))
                    throw new RuntimeException("return type of the starter method must be Action");
                method.setAccessible(true);
                stateMethods.put(method.getName(), method);
                stateAnnotations.put(method.getName(), method.getAnnotation(State.class));
            }
        }
        if (starterMethod == null)
            throw new RuntimeException("starter method could not found");
    }

    public synchronized void run() throws Exception {
        if (runCalled) throw new IllegalStateException("run can only called once");
        runCalled = true;

        handleActionResult((Action) starterMethod.invoke(machine, machineContext));
    }

    private synchronized void handleActionResult(Action actionRes) throws Exception {
        if (actionRes instanceof Action.NoOperation) {

        } else if (actionRes instanceof Action.CloseConnection) {
            sctpClient.close();
        } else if (actionRes instanceof Action.SendData) {
            var oldState = currentState;
            var action = (Action.SendData) actionRes;
            currentState = action.nextState;

            int timeout = oldState == null ? 0 : stateAnnotations.get(oldState).timeout();
            sctpClient.send(action.streamNumber, action.data, this::handleMessage, timeout);
        } else if (actionRes instanceof Action.SendElement) {
            var oldState = currentState;
            var action = (Action.SendElement) actionRes;
            currentState = action.nextState;

            int timeout = oldState == null ? 0 : stateAnnotations.get(oldState).timeout();
            byte[] data = MTSAdapter.encodeAper(action.schema, action.element);
            sctpClient.send(action.streamNumber, data, this::handleMessage, timeout);
        } else if (actionRes instanceof Action.SwitchState) {
            var action = (Action.SwitchState) actionRes;
            currentState = action.nextState;
            handleMessage(action.msgCtx);
        } else {
            throw new RuntimeException("unhandled action result");
        }
    }

    private synchronized void handleMessage(byte[] receivedBytes, MessageInfo messageInfo, SctpChannel channel) throws Exception {
        var messageContext = new MessageContext(receivedBytes, messageInfo.streamNumber());
        handleMessage(messageContext);
    }

    private synchronized void handleMessage(MessageContext messageContext) throws Exception {
        var stateMethod = stateMethods.get(currentState);
        if (stateMethod == null)
            throw new RuntimeException("state method could not found: " + currentState);

        handleActionResult((Action) stateMethod.invoke(machine, machineContext, messageContext));
    }

    public HashMap getMachineInfo() {
        var states = new ArrayList<String>();
        for (var method : this.stateMethods.values())
            states.add(method.getName());

        var inf = new HashMap<>();
        inf.put("machineName", this.machineType.getSimpleName());
        inf.put("starter", this.starterMethod.getName());
        inf.put("states", states);
        return inf;
    }
}
