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

package tr.havelsan.ueransim.app.utils;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import tr.havelsan.ueransim.app.common.testcmd.*;
import tr.havelsan.ueransim.core.Constants;
import tr.havelsan.ueransim.mts.MtsContext;
import tr.havelsan.ueransim.mts.MtsException;
import tr.havelsan.ueransim.nas.eap.*;
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

    public static void initTestingMts(MtsContext mts) {
        initDefaultMts(mts);

        mts.typeRegistry.registerTypeName("SLEEP", TestCmd_Sleep.class);
        mts.typeRegistry.registerTypeName("INITIAL_REGISTRATION", TestCmd_InitialRegistration.class);
        mts.typeRegistry.registerTypeName("PERIODIC_REGISTRATION", TestCmd_PeriodicRegistration.class);
        mts.typeRegistry.registerTypeName("DEREGISTRATION", TestCmd_Deregistration.class);
        mts.typeRegistry.registerTypeName("PDU_SESSION_ESTABLISHMENT", TestCmd_PduSessionEstablishment.class);
        mts.typeRegistry.registerTypeName("PING", TestCmd_Ping.class);
    }
}