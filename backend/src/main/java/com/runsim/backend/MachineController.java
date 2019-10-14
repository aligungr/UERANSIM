package com.runsim.backend;

import com.runsim.backend.annotations.Starter;
import com.runsim.backend.annotations.State;
import com.runsim.backend.annotations.StateMachine;
import com.runsim.backend.mts.MTSAdapter;
import com.runsim.backend.sctp.SCTPClient;
import com.sun.nio.sctp.MessageInfo;
import com.sun.nio.sctp.SctpChannel;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MachineController {
    private final Class machineType;
    private final Object machine;
    private Method starterMethod;
    private Map<String, Method> stateMethods;
    private Map<String, State> stateAnnotations;
    private String currentState;
    private final SCTPClient sctpClient;
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

        for (Method method : machine.getClass().getMethods()) {
            if (method.isAnnotationPresent(Starter.class)) {
                if (starterMethod != null)
                    throw new RuntimeException("multiple starters are not allowed");
                Parameter[] params = method.getParameters();
                if (params.length != 1 || params[0].getType() != MachineContext.class)
                    throw new RuntimeException("bad parameters for starter method");
                if (!Action.class.isAssignableFrom(method.getReturnType()))
                    throw new RuntimeException("return type of the starter method must be Action");
                method.setAccessible(true);
                starterMethod = method;
            }
            if (method.isAnnotationPresent(State.class)) {
                Parameter[] params = method.getParameters();
                if (params.length != 2 || params[0].getType() != MessageContext.class || params[1].getType() != MachineContext.class)
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
            String oldState = currentState;
            Action.SendData action = (Action.SendData) actionRes;
            currentState = action.getNextState();

            int timeout = oldState == null ? 0 : stateAnnotations.get(oldState).timeout();
            sctpClient.send(action.getStreamNumber(), action.getData(), this::handleMessage, timeout);
        } else if (actionRes instanceof Action.SendElement) {
            String oldState = currentState;
            Action.SendElement action = (Action.SendElement) actionRes;
            currentState = action.getNextState();

            int timeout = oldState == null ? 0 : stateAnnotations.get(oldState).timeout();
            byte[] data = MTSAdapter.encodeAper(action.getSchema(), action.getElement());
            sctpClient.send(action.getStreamNumber(), data, this::handleMessage, timeout);
        } else {
            throw new RuntimeException("unhandled action result");
        }
    }

    private synchronized void handleMessage(byte[] receivedBytes, MessageInfo messageInfo, SctpChannel channel) throws Exception {
        Method stateMethod = stateMethods.get(currentState);
        if (stateMethod == null)
            throw new RuntimeException("state method could not found: " + currentState);

        MessageContext messageContext = new MessageContext(receivedBytes, messageInfo.streamNumber());
        handleActionResult((Action) stateMethod.invoke(machine, messageContext, machineContext));
    }

    public HashMap getMachineInfo() {
        var states = new ArrayList<String>();
        for (Method method : this.stateMethods.values())
            states.add(method.getName());

        var inf = new HashMap<>();
        inf.put("machine-name", this.machineType.getSimpleName());
        inf.put("starter", this.starterMethod.getName());
        inf.put("states", states);
        return inf;
    }
}
