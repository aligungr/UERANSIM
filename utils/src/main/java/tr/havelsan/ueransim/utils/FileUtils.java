/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.util.concurrent.atomic.AtomicBoolean;

/*
 * This class shall be used for file writing operations inside of UERANSIM project.
 * Purpose of this is to perform unprivileged file operations even if the agent runs with 'sudo'.
 */
public class FileUtils {

    private static final AtomicBoolean isInitialized = new AtomicBoolean();
    private static String referenceFile;

    public static void initialize(String referenceFile) {
        if (isInitialized.getAndSet(true))
            throw new IllegalStateException();

        FileUtils.referenceFile = referenceFile;
    }

    public static File createFile(String path) {
        var file = new File(path);
        try {
            boolean isCreated = file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            copyPermissions(new File(referenceFile), file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return file;
    }

    public static File createDir(String path) {
        var file = new File(path);
        boolean isCreated = file.mkdir();

        try {
            copyPermissions(new File(referenceFile).getParentFile(), file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return file;
    }

    public static void appendToFile(String path, byte[] data) {
        var p = Paths.get(path);

        if (!Files.exists(p)) {
            createFile(p.toAbsolutePath().toString());
        }

        try {
            Files.write(p, data, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void appendToFile(String path, String content) {
        appendToFile(path, content.getBytes(StandardCharsets.UTF_8));
    }

    private static void copyPermissions(File fromFile, File toFile) throws IOException {
        var fromAttr = Files.readAttributes(fromFile.toPath(), PosixFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
        var toAttr = Files.getFileAttributeView(toFile.toPath(), PosixFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);

        toAttr.setGroup(fromAttr.group());
        toAttr.setOwner(fromAttr.owner());
        toAttr.setPermissions(fromAttr.permissions());
    }
}
