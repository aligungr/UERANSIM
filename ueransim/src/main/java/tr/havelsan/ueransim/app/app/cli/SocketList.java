/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.app.cli;

import tr.havelsan.ueransim.app.common.nts.IwCliServerMessage;
import tr.havelsan.ueransim.utils.BiMap;
import tr.havelsan.ueransim.utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.*;

class SocketList {
    private final Set<Socket> sockets;
    private final Set<Socket> markedSockets; // sockets that recently sent heartbeat
    private final Set<Socket> buffer;

    private final Map<Socket, InputStream> inputStreams;
    private final Map<Socket, OutputStream> outputStreams;
    private final BiMap<Socket, UUID> clientIds;

    public SocketList() {
        this.sockets = new HashSet<>();
        this.markedSockets = new HashSet<>();
        this.buffer = new HashSet<>();

        this.inputStreams = new HashMap<>();
        this.outputStreams = new HashMap<>();
        this.clientIds = new BiMap<>();
    }

    public void add(Socket socket, InputStream input, OutputStream output) {
        sockets.add(socket);
        markedSockets.add(socket);
        inputStreams.put(socket, input);
        outputStreams.put(socket, output);
        clientIds.put(socket, UUID.randomUUID());
    }

    public void close(Socket socket) {
        var inputStream = inputStreams.get(socket);
        var outputStream = outputStreams.get(socket);

        if (inputStream != null) Utils.runUnchecked(inputStream::close);
        if (outputStream != null) Utils.runUnchecked(outputStream::close);
        if (socket != null) Utils.runUnchecked(socket::close);

        sockets.remove(socket);
        markedSockets.remove(socket);
        inputStreams.remove(socket);
        outputStreams.remove(socket);
        clientIds.removeByKey(socket);
    }

    public void mark(Socket socket) {
        markedSockets.add(socket);
    }

    public void sweep() {
        buffer.clear();
        for (var socket : sockets) {
            if (!markedSockets.contains(socket)) {
                buffer.add(socket);
            }
        }

        markedSockets.clear();
        for (var socket : buffer) {
            close(socket);
        }

        buffer.clear();
    }

    public UUID getId(Socket socket) {
        return clientIds.getValue(socket);
    }

    public void writeToClient(UUID client, byte[] data) {
        if (client == null)
            return;

        var sockets = new ArrayList<Socket>();

        if (client == IwCliServerMessage.BROADCAST) {
            for (var c : clientIds.valueSet()) {
                var socket = clientIds.getKey(c);
                sockets.add(socket);
            }
        } else {
            sockets.add(clientIds.getKey(client));
        }

        for (var socket : sockets) {
            if (socket == null) continue;

            var output = outputStreams.get(socket);
            if (output == null) {
                close(socket);
                continue;
            }

            try {
                output.write(data);
                output.flush();
            } catch (IOException e) {
                close(socket);
                continue;
            }
        }
    }
}
