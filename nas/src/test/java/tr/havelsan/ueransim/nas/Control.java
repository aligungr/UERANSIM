/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.utils.Constants;
import tr.havelsan.ueransim.utils.exceptions.IncorrectImplementationException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.function.Consumer;

class Control {

    static Method getMethod(Class<?> type, String methodName, Class<?>... argTypes) {
        try {
            return type.getDeclaredMethod(methodName, argTypes);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    static Method[] getAllMethods(Class<?> type) {
        return type.getDeclaredMethods();
    }

    static Constructor getConstructor(Class<?> type, Class<?>... argTypes) {
        try {
            return type.getDeclaredConstructor(argTypes);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    static boolean sameVisibility(int modifiers, Visibility visibility) {
        if (visibility == Visibility.PUBLIC && !Modifier.isPublic(modifiers))
            return false;
        if (visibility == Visibility.PRIVATE && !Modifier.isPrivate(modifiers))
            return false;
        if (visibility == Visibility.PROTECTED && !Modifier.isPrivate(modifiers))
            return false;
        if (visibility == Visibility.INTERNAL && (Modifier.isPrivate(modifiers) || Modifier.isProtected(modifiers) || Modifier.isPublic(modifiers)))
            return false;
        return true;
    }

    static boolean methodExists(Class type, boolean isStatic, Visibility visibility, String methodName, Class returnType, Class... argTypes) {
        Method method = getMethod(type, methodName, argTypes);
        if (method == null) return false;
        if (Modifier.isStatic(method.getModifiers()) != isStatic) return false;
        if (visibility != null && !sameVisibility(method.getModifiers(), visibility)) return false;
        return true;
    }

    static boolean parameterPrefixMatch(Object[] base, Object[] prefix) {
        if (base.length < prefix.length) return false;
        int min = Math.min(base.length, prefix.length);
        for (int i = 0; i < min; i++)
            if (!base[i].equals(prefix[i])) return false;
        return true;
    }

    static boolean methodExistsArgsPrefix(Class type, boolean isStatic, Visibility visibility, String methodName, Class returnType, Class... argTypes) {
        return Arrays.stream(getAllMethods(type))
                .filter(method -> Modifier.isStatic(method.getModifiers()) == isStatic)
                .filter(method -> sameVisibility(method.getModifiers(), visibility))
                .filter(method -> method.getName().equals(methodName))
                .filter(method -> method.getReturnType().equals(returnType))
                .anyMatch(method -> parameterPrefixMatch(Arrays.stream(method.getParameters())
                        .map(Parameter::getType).toArray(), argTypes));
    }

    static boolean constructorExists(Class type, Visibility visibility, Class... argTypes) {
        Constructor method = getConstructor(type, argTypes);
        if (method == null) return false;
        if (visibility == null) return true;
        return sameVisibility(method.getModifiers(), visibility);
    }

    static boolean nonStaticFieldExists(Class type) {
        var fields = type.getDeclaredFields();
        for (var field : fields) {
            if (!Modifier.isStatic(field.getModifiers()))
                return true;
        }
        return false;
    }

    static boolean nonFinalFieldExists(Class type) {
        var fields = type.getDeclaredFields();
        for (var field : fields) {
            if (!Modifier.isFinal(field.getModifiers()))
                return true;
        }
        return false;
    }

    static boolean fieldTypeExists(Class type, Class fieldType) {
        var fields = type.getDeclaredFields();
        for (var field : fields) {
            if (field.getType().equals(fieldType)) return true;
        }
        return false;
    }

    static boolean allFieldsOfTypesOrLists(Class<?> type, Class<?>... types) {
        var fields = type.getDeclaredFields();
        for (var field : fields) {
            if (Arrays.stream(types).noneMatch(clazz -> clazz.isAssignableFrom(field.getType())))
                return false;
        }
        return true;
    }

    static Class<?> getFieldType(Class<?> type, String fieldName) {
        try {
            return type.getField(fieldName).getType();
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

    static <T extends ProtocolEnum> String findFieldNameOfProtocolEnum(T value) {
        var clazz = value.getClass();
        for (var field : clazz.getDeclaredFields()) {
            Object val = null;
            try {
                val = field.get(null);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            if (val.equals(value))
                return field.getName();
        }
        return null;
    }

    static boolean fieldVisibilityExists(Class type, Visibility visibility) {
        var fields = type.getDeclaredFields();
        for (var field : fields) {
            int modifiers = field.getModifiers();
            if (visibility == Visibility.PUBLIC && Modifier.isPublic(modifiers))
                return true;
            if (visibility == Visibility.PRIVATE && Modifier.isPrivate(modifiers))
                return true;
            if (visibility == Visibility.PROTECTED && Modifier.isPrivate(modifiers))
                return true;
            if (visibility == Visibility.INTERNAL && !(Modifier.isPrivate(modifiers) || Modifier.isProtected(modifiers) || Modifier.isPublic(modifiers)))
                return true;
            return false;
        }
        return false;
    }

    static boolean fieldVisibilityAll(Class type, Visibility visibility) {
        if (visibility == Visibility.PUBLIC) {
            if (fieldVisibilityExists(type, Visibility.PRIVATE)) return false;
            if (fieldVisibilityExists(type, Visibility.PROTECTED)) return false;
            if (fieldVisibilityExists(type, Visibility.INTERNAL)) return false;
            return true;
        }
        if (visibility == Visibility.PROTECTED) {
            if (fieldVisibilityExists(type, Visibility.PRIVATE)) return false;
            if (fieldVisibilityExists(type, Visibility.PUBLIC)) return false;
            if (fieldVisibilityExists(type, Visibility.INTERNAL)) return false;
            return true;
        }
        if (visibility == Visibility.PRIVATE) {
            if (fieldVisibilityExists(type, Visibility.PROTECTED)) return false;
            if (fieldVisibilityExists(type, Visibility.PUBLIC)) return false;
            if (fieldVisibilityExists(type, Visibility.INTERNAL)) return false;
            return true;
        }
        if (visibility == Visibility.INTERNAL) {
            if (fieldVisibilityExists(type, Visibility.PROTECTED)) return false;
            if (fieldVisibilityExists(type, Visibility.PUBLIC)) return false;
            if (fieldVisibilityExists(type, Visibility.PRIVATE)) return false;
            return true;
        }
        return true;
    }

    static boolean staticFieldExists(Class type) {
        var fields = type.getDeclaredFields();
        for (var field : fields) {
            if (Modifier.isStatic(field.getModifiers()))
                return true;
        }
        return false;
    }

    static boolean isInnerClass(Class type) {
        return type.getEnclosingClass() != null && !Modifier.isStatic(type.getModifiers());
    }

    static boolean constructorCount(Class type, int expectedCount) {
        return type.getDeclaredConstructors().length == expectedCount;
    }

    static ClassGraph makeClassGraph() {
        return new ClassGraph().enableClassInfo().ignoreClassVisibility().whitelistPackages(Constants.NAS_IMPL_PREFIX);
    }

    static <T> void controlForType(Class<T> type, boolean includeAbstracts, Consumer<Class> control) {
        try (ScanResult scanResult = Control.makeClassGraph().scan()) {
            var classInfoList = scanResult.getAllClasses();
            if (classInfoList.size() == 0)
                throw new IncorrectImplementationException("no class found for type: " + type);

            for (var classInfo : classInfoList) {
                Class<?> clazz;
                try {
                    clazz = Class.forName(classInfo.getName());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                if (!includeAbstracts && Modifier.isAbstract(clazz.getModifiers()))
                    continue;

                if (type.isAssignableFrom(clazz)) {
                    control.accept(clazz);
                }
            }
        }
    }
}