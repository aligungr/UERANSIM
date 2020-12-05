package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnNull;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_GapConfig;

public class RRC_SetupRelease_GapConfig extends AsnChoice {
    public AsnNull release;
    public RRC_GapConfig setup;
}

