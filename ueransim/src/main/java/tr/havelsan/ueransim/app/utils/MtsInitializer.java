/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.utils;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import tr.havelsan.ueransim.app.common.testcmd.*;
import tr.havelsan.ueransim.mts.MtsContext;
import tr.havelsan.ueransim.mts.MtsException;
import tr.havelsan.ueransim.nas.eap.*;
import tr.havelsan.ueransim.utils.Constants;
import tr.havelsan.ueransim.utils.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class MtsInitializer {

    public static void initDefaultMts(MtsContext mts) {
        try (ScanResult scanResult = new ClassGraph().enableClassInfo().ignoreClassVisibility().whitelistPackages(Constants.NAS_IMPL_PREFIX).scan()) {
            var classInfoList = scanResult.getAllClasses();
            for (var classInfo : classInfoList) {
                Class<?> clazz;
                try {
                    clazz = Class.forName(classInfo.getName());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                String typeName = Utils.getTypeName(clazz);
                mts.typeRegistry.registerTypeName(typeName, clazz);
            }
        }

        final Class<?>[] otherTypes = new Class[]{
                Eap.class,
                EapAkaPrime.class,
                EapIdentity.class,
                EapNotification.class,
                EAttributeType.class,
                ESubType.class,
                Eap.ECode.class,
                Eap.EEapType.class
        };

        for (var type : otherTypes) {
            mts.typeRegistry.registerTypeName(type.getSimpleName(), type);
        }

        mts.typeRegistry.registerCustomType(new MtsProtocolEnumRegistry());

        mts.decoder.setFileProvider((searchDir, path) -> {
            try {
                String content = Files.readString(Paths.get(searchDir, path));
                if (path.endsWith(".yaml"))
                    content = Utils.convertYamlToJson(content);
                return content;
            } catch (NoSuchFileException e) {
                throw new MtsException("file not found: %s", e.getFile());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}