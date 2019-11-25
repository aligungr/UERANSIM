package com.runsim.backend.demo.control;

import com.runsim.backend.exceptions.IncorrectImplementationException;
import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.nas.core.ProtocolEnum;
import com.runsim.backend.nas.core.ies.InformationElement;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.bits.BitN;
import com.runsim.backend.utils.octets.OctetN;
import com.runsim.backend.utils.octets.OctetString;

import java.util.List;

import static com.runsim.backend.demo.control.Control.*;

public class ImplementationControl {

    private static final Class[] USUAL_TYPES = new Class[]{
            OctetN.class, BitN.class, InformationElement.class, ProtocolEnum.class, NasValue.class,
            String.class, OctetString.class, List.class,
            OctetN[].class, BitN[].class, InformationElement[].class, ProtocolEnum[].class, NasValue[].class,
            String[].class, OctetString[].class
    };

    public static void main(String[] args) {
        controlForType(ProtocolEnum.class, false, ImplementationControl::controlProtocolEnums);
        controlForType(NasValue.class, false, ImplementationControl::controlNasValues);
        controlForType(NasValue.class, true, ImplementationControl::controlNasAbstractValues);

        controlMessages();
        controlInformationElements();
    }

    private static void controlProtocolEnums(Class clazz) {
        if (!methodExists(clazz, true, Visibility.PUBLIC, "fromValue", clazz, int.class))
            throw new IncorrectImplementationException(clazz, "All protocol enums must provide public static method: fromValue that returns itself and accepts an integer as an argument");
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

    private static void controlNasValues(Class clazz) {
        if (staticFieldExists(clazz))
            throw new IncorrectImplementationException(clazz, "NasValue should not contain static field");
        if (!fieldVisibilityAll(clazz, Visibility.PUBLIC))
            throw new IncorrectImplementationException(clazz, "NasValue should only contain public fields");
        if (isInnerClass(clazz))
            throw new IncorrectImplementationException(clazz, "NasValue should not be inner class");
        if (!constructorExists(clazz, Visibility.PUBLIC))
            throw new IncorrectImplementationException(clazz, "NasValue should be provide at least one public empty constructor");
        if (!methodExistsArgsPrefix(clazz, true, Visibility.PUBLIC, "decode", clazz, OctetInputStream.class))
            throw new IncorrectImplementationException(clazz, "NasValue should provide method: 'public static [same-type] decode(OctetInputStream, ...)'");
        if (fieldTypeExists(clazz, OctetN.class))
            throw new IncorrectImplementationException(clazz, "do not use OctetN as field type directly. Use Octet, Octet2, Octet3, ... ");
        if (fieldTypeExists(clazz, BitN.class))
            throw new IncorrectImplementationException(clazz, "do not use BitN as field type directly. Use Bit, Bit2, Bit3, ... ");
        if (!allFieldsOfTypesOrLists(clazz, USUAL_TYPES))
            throw new IncorrectImplementationException(clazz, "NasValue contains a field with a type that should not be used directly.");
    }

    private static void controlNasAbstractValues(Class clazz) {
        if (!methodExistsArgsPrefix(clazz, true, Visibility.PUBLIC, "decode", clazz, OctetInputStream.class))
            throw new IncorrectImplementationException(clazz, "NasValue should provide method: 'public static [same-type] decode(OctetInputStream, ...)'");
    }

    private static void controlInformationElements() {

    }

    private static void controlMessages() {

    }
}
