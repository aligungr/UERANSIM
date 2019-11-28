package com.runsim.backend.demo.control;

import com.runsim.backend.exceptions.IncorrectImplementationException;
import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.nas.core.ProtocolEnum;
import com.runsim.backend.nas.core.ies.InformationElement;
import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.nas.core.messages.NasMessage;
import com.runsim.backend.nas.core.messages.PlainMmMessage;
import com.runsim.backend.nas.core.messages.PlainSmMessage;
import com.runsim.backend.nas.eap.EAP;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.bits.BitN;
import com.runsim.backend.utils.octets.OctetN;
import com.runsim.backend.utils.octets.OctetString;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import static com.runsim.backend.demo.control.Control.*;

public class ImplementationControl {

    private static final Class<?>[] USUAL_TYPES = new Class<?>[]{
            OctetN.class, BitN.class, InformationElement.class, ProtocolEnum.class, NasValue.class,
            String.class, OctetString.class, List.class, EAP.class,

            OctetN[].class, BitN[].class, InformationElement[].class, ProtocolEnum[].class, NasValue[].class,
            String[].class, OctetString[].class, List[].class, EAP[].class,
    };

    public static void main(String[] args) {
        controlForType(ProtocolEnum.class, false, ImplementationControl::controlProtocolEnums);
        controlForType(NasValue.class, false, ImplementationControl::controlNasValues);
        controlForType(NasValue.class, true, ImplementationControl::controlNasAbstractValues);
        controlForType(InformationElement.class, true, ImplementationControl::controlInformationElements);
        controlForType(NasMessage.class, true, ImplementationControl::controlMessages);
    }

    private static void controlProtocolEnums(Class<?> clazz) {
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

    private static void controlNasValues(Class<?> clazz) {
        if (staticFieldExists(clazz))
            throw new IncorrectImplementationException(clazz, "NasValue should not contain static field");
        if (!fieldVisibilityAll(clazz, Visibility.PUBLIC))
            throw new IncorrectImplementationException(clazz, "NasValue should only contain public fields");
        if (isInnerClass(clazz))
            throw new IncorrectImplementationException(clazz, "NasValue should not be inner class");
        if (!constructorExists(clazz, Visibility.PUBLIC))
            throw new IncorrectImplementationException(clazz, "NasValue should be provide at least one public empty constructor");
        if (!methodExists(clazz, true, Visibility.PUBLIC, "decode", clazz, OctetInputStream.class))
            throw new IncorrectImplementationException(clazz, "NasValue should provide method: 'public static [same-type] decode(OctetInputStream)'");
        if (fieldTypeExists(clazz, OctetN.class))
            throw new IncorrectImplementationException(clazz, "do not use OctetN as field type directly. Use Octet, Octet2, Octet3, ... ");
        if (fieldTypeExists(clazz, BitN.class))
            throw new IncorrectImplementationException(clazz, "do not use BitN as field type directly. Use Bit, Bit2, Bit3, ... ");
        if (!allFieldsOfTypesOrLists(clazz, USUAL_TYPES))
            throw new IncorrectImplementationException(clazz, "NasValue contains a field with a type that should not be used directly.");
    }

    private static void controlNasAbstractValues(Class<?> clazz) {
        if (!methodExistsArgsPrefix(clazz, true, Visibility.PUBLIC, "decode", clazz, OctetInputStream.class))
            throw new IncorrectImplementationException(clazz, "NasValue should provide method: 'public static [same-type] decode(OctetInputStream, ...)'");
    }

    private static void controlInformationElements(Class<?> clazz) {
        if (!fieldVisibilityAll(clazz, Visibility.PUBLIC))
            throw new IncorrectImplementationException(clazz, "IE should only contain public fields");
        if (isInnerClass(clazz))
            throw new IncorrectImplementationException(clazz, "IE should not be an inner class, but might be a nested class");
        if (!constructorExists(clazz, Visibility.PUBLIC))
            throw new IncorrectImplementationException(clazz, "IE should be provide at least one public empty constructor");
        if (fieldTypeExists(clazz, OctetN.class))
            throw new IncorrectImplementationException(clazz, "do not use OctetN as field type directly. Use Octet, Octet2, Octet3, ... ");
        if (fieldTypeExists(clazz, BitN.class))
            throw new IncorrectImplementationException(clazz, "do not use BitN as field type directly. Use Bit, Bit2, Bit3, ... ");
        if (!allFieldsOfTypesOrLists(clazz, USUAL_TYPES))
            throw new IncorrectImplementationException(clazz, "IE contains a field with a type that should not be used directly.");
    }

    private static void controlMessages(Class<?> clazz) {
        if (staticFieldExists(clazz))
            throw new IncorrectImplementationException(clazz, "NasMessage should not contain static field");
        if (!fieldVisibilityAll(clazz, Visibility.PUBLIC))
            throw new IncorrectImplementationException(clazz, "NasMessage should only contain public fields");
        if (isInnerClass(clazz))
            throw new IncorrectImplementationException(clazz, "NasMessage should not be inner class");
        if (!constructorExists(clazz, Visibility.PUBLIC))
            throw new IncorrectImplementationException(clazz, "NasMessage should be provide at least one public empty constructor");
        if (!allFieldsOfTypesOrLists(clazz, InformationElement.class))
            throw new IncorrectImplementationException(clazz, "NasMessage fields cannot be a type other than InformationElement and subtypes.");

        NasMessage instance;
        try {
            instance = (NasMessage) clazz.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        EMessageType messageType;
        if (instance instanceof PlainSmMessage) {
            var mess = (PlainSmMessage) instance;
            if (mess.messageType.isMobilityManagement())
                throw new IncorrectImplementationException(clazz, "Incorrect message type enum used");
            messageType = mess.messageType;
        } else if (instance instanceof PlainMmMessage) {
            var mess = (PlainMmMessage) instance;
            if (!mess.messageType.isMobilityManagement())
                throw new IncorrectImplementationException(clazz, "Incorrect message type enum used");
            messageType = mess.messageType;
        } else {
            throw new IncorrectImplementationException(clazz, "NasMessage should extends PlainSmMessage or PlainMmMessage");
        }

        String enumName = findFieldNameOfProtocolEnum(messageType);
        if (enumName == null)
            throw new IncorrectImplementationException(clazz, "invalid messageType enum value");

        enumName = enumName.replace("_", "");
        enumName = enumName.toLowerCase(Locale.ENGLISH);

        String className = clazz.getSimpleName();
        className = className.replace("_", "");
        className = className.toLowerCase(Locale.ENGLISH);

        if (!enumName.equals(className)) {
            throw new IncorrectImplementationException(clazz, "Possible wrong message type or wrong message class name");
        }

        controlMessageBuilder(clazz, instance);
    }

    private static void controlMessageBuilder(Class<?> clazz, NasMessage instance) {
        var ie1 = new HashSet<String>();
        var ieN = new HashSet<String>();
        var ieAll = new HashSet<String>();
        var ieiAll = new HashSet<Integer>();

        var builder = new IMessageBuilder() {
            @Override
            public void mandatoryIE(String field) {
                if (ieAll.contains(field))
                    throw new IncorrectImplementationException(clazz, "ie occurs more than once: " + field);
                ieN.add(field);
                ieAll.add(field);
            }

            @Override
            public void optionalIE(int iei, String field) {
                if (ieAll.contains(field))
                    throw new IncorrectImplementationException(clazz, "ie occurs more than once: " + field);
                if (ieiAll.contains(iei))
                    throw new IncorrectImplementationException(clazz, "ie, occurs more than once: " + iei);
                ieiAll.add(iei);
                ieN.add(field);
                ieAll.add(field);
            }

            @Override
            public void mandatoryIE1(String field1, String field0) {
                if (ieAll.contains(field1))
                    throw new IncorrectImplementationException(clazz, "ie occurs more than once: " + field1);
                if (ieAll.contains(field0))
                    throw new IncorrectImplementationException(clazz, "ie occurs more than once: " + field0);
                ie1.add(field1);
                ie1.add(field0);
                ieAll.add(field1);
                ieAll.add(field0);
            }

            @Override
            public void mandatoryIE1(String field) {
                if (ieAll.contains(field))
                    throw new IncorrectImplementationException(clazz, "ie occurs more than once: " + field);
                ie1.add(field);
                ieAll.add(field);
            }

            @Override
            public void optionalIE1(int iei, String field) {
                if (ieAll.contains(field))
                    throw new IncorrectImplementationException(clazz, "ie occurs more than once: " + field);
                if (ieiAll.contains(iei))
                    throw new IncorrectImplementationException(clazz, "ie, occurs more than once: " + ie1);
                ieiAll.add(iei);
                ie1.add(field);
                ieAll.add(field);
            }
        };

        instance.build(builder);

        for (var field : ie1) {
            var type = getFieldType(clazz, field);
            if (type == null || (!InformationElement1.class.isAssignableFrom(type)))
                throw new IncorrectImplementationException(clazz, "ie1 registered type must be InformationElement1: " + field);
        }

        for (var field : ieN) {
            var type = getFieldType(clazz, field);
            if (type == null || (InformationElement1.class.isAssignableFrom(type)))
                throw new IncorrectImplementationException(clazz, "not ie1 registered type must not be InformationElement1: " + field);
        }
    }
}
