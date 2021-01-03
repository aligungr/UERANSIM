/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.app.entry;

import picocli.CommandLine;

import java.util.function.Consumer;

@CommandLine.Command(name = "nr-agent",
        mixinStandardHelpOptions = true,
        versionProvider = ClientApp.VersionProvider.class
)
public class AgentParams implements Runnable {
    private final Consumer<AgentParams> consumer;

    @CommandLine.Option(
            names = {"-R", "--no-route-config"},
            description = "Do not auto configure routing for UE TUN interface. Default is false."
    )
    public boolean noRouteConfig;

    public AgentParams(Consumer<AgentParams> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {
        consumer.accept(this);
    }
}
