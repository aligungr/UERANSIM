//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include <common.hpp>
#include <gnb.hpp>

bool isOgs = true; // or free5gc

int mcc = isOgs ? 901 : 208;
int mnc = isOgs ? 70 : 93;
const char *key = isOgs ? "465B5CE8B199B49FAA5F0A2EE238A6BC" : "8baf473f2f8fd09487cccbd7097c6862";
const char *opC = isOgs ? "E8ED289DEBA952E4283B54E88E6183CA" : "8e27b6af0e692e750f32667a3b14605d";
const char *supi = isOgs ? "imsi-901700000000003" : "imsi-208930000000003";
const char *ip = isOgs ? "192.168.1.55" : "192.168.1.48";

int main()
{
    auto *gnbConfig = new nr::gnb::GnbConfig();
    gnbConfig->gnbIdLength = 32;
    gnbConfig->nci = 0x0000000100;
    gnbConfig->name = "UERANSIM-gnb1";
    gnbConfig->amfConfigs.push_back({ip, 38412});
    gnbConfig->portalIp = "192.168.1.37";
    gnbConfig->ngapIp = "192.168.1.37";
    gnbConfig->gtpIp = "192.168.1.37";
    gnbConfig->nssais.push_back(SliceSupport{1, octet3{0x010203}});
    gnbConfig->pagingDrx = EPagingDrx::V128;
    gnbConfig->plmn.mcc = mcc;
    gnbConfig->plmn.mnc = mnc;
    gnbConfig->plmn.isLongMnc = false;
    gnbConfig->tac = 1;
    gnbConfig->ignoreStreamIds = true;

    auto *gnb = new nr::gnb::GNodeB(gnbConfig, nullptr);
    gnb->start();

    while (true)
        utils::Sleep(1000);
}