/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
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
