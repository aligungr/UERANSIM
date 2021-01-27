//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include <common.hpp>
#include <ue.hpp>

bool isOgs = true; // or free5gc

int mcc = isOgs ? 901 : 208;
int mnc = isOgs ? 70 : 93;
const char *key = isOgs ? "465B5CE8B199B49FAA5F0A2EE238A6BC" : "8baf473f2f8fd09487cccbd7097c6862";
const char *opC = isOgs ? "E8ED289DEBA952E4283B54E88E6183CA" : "8e27b6af0e692e750f32667a3b14605d";
const char *supi = isOgs ? "imsi-901700000000003" : "imsi-208930000000003";
const char *ip = isOgs ? "192.168.1.55" : "192.168.1.48";

int main()
{
    auto sess1 = nr::ue::SessionConfig{};
    sess1.type = nas::EPduSessionType::IPV4;
    sess1.apn = "internet";
    sess1.sNssai = SliceSupport{1, octet3{0x010203}};

    auto *ueConfig = new nr::ue::UeConfig();
    ueConfig->nssais.push_back(SliceSupport{1, octet3{0x010203}});
    ueConfig->plmn.mcc = mcc;
    ueConfig->plmn.mnc = mnc;
    ueConfig->plmn.isLongMnc = false;
    ueConfig->emulationMode = true;
    ueConfig->key = OctetString::FromHex(key);
    ueConfig->opC = OctetString::FromHex(opC);
    ueConfig->opType = nr::ue::OpType::OP;
    ueConfig->amf = OctetString::FromHex("8000");
    ueConfig->imei = "356938035643803";
    ueConfig->supi = Supi::Parse(supi);
    ueConfig->gnbSearchList = {"192.168.1.37"};
    ueConfig->initSessions = {sess1};
    ueConfig->configureRouting = true;

    auto *ue = new nr::ue::UserEquipment(ueConfig, nullptr);
    ue->start();

    while (true)
        utils::Sleep(1000);
}