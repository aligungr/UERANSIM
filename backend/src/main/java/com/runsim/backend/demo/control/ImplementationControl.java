package com.runsim.backend.demo.control;

import com.runsim.backend.exceptions.IncorrectImplementationException;
import com.runsim.backend.nas.core.ProtocolEnum;

import static com.runsim.backend.demo.control.Control.*;

public class ImplementationControl {

    public static void main(String[] args) {
        controlForType(ProtocolEnum.class, false, ImplementationControl::controlProtocolEnums);

        controlNasValues();
        controlMessages();
        controlInformationElements();
    }

    private static void controlProtocolEnums(Class clazz) {
        if (!methodExists(clazz, true, Visibility.PUBLIC, "fromValue", clazz, int.class))
            throw new IncorrectImplementationException(clazz, "All protocol enums must provide public method: fromValue that returns itself and accepts an integer as an argument");
        if (!constructorCount(clazz, 1))
            throw new IncorrectImplementationException(clazz, "All protocol enums must provide one (1) constructor");
        if (!constructorExists(clazz, Visibility.PRIVATE, int.class, String.class))
            throw new IncorrectImplementationException(clazz, "All protocol enums must provide private (int, String) constructor");
        if (nonStaticFieldExists(clazz))
            throw new IncorrectImplementationException(clazz, "Protocol enums should not contain non static fields");
        if (!fieldVisibilityAll(clazz, Visibility.PUBLIC))
            throw new IncorrectImplementationException(clazz, "Protocol enums must have public fields only");
        if (nonFinalFieldExists(clazz))
            throw new IncorrectImplementationException(clazz, "All fields must be final for protocol enums");
    }

    private static void controlNasValues() {

    }

    private static void controlInformationElements() {

    }

    private static void controlMessages() {

    }
}
