//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include <utils/common.hpp>
#include <utils/constants.hpp>
#include <cxxopts/cxxopts.hpp>
#include <gnb/gnb.hpp>
#include <iostream>
#include <unistd.h>
#include <yaml-cpp/yaml.h>
#include <utils/yaml_utils.hpp>

static nr::gnb::GnbConfig *ReadConfigYaml(const std::string &file)
{
    auto *result = new nr::gnb::GnbConfig();
    auto config = YAML::LoadFile(file);

    result->plmn.mcc = yaml::GetInt32(config, "mcc", 1, 999);
    yaml::GetString(config, "mcc", 3, 3);
    result->plmn.mnc = yaml::GetInt32(config, "mnc", 0, 999);
    result->plmn.isLongMnc = yaml::GetString(config, "mnc", 2, 3).size() == 3;

    result->nci = yaml::GetInt64(config, "nci", 0, 0xFFFFFFFFFll);
    result->gnbIdLength = yaml::GetInt32(config, "idLength", 22, 32);
    result->tac = yaml::GetInt32(config, "tac", 0, 0xFFFFFF);

    result->portalIp = yaml::GetIp4(config, "linkIp");
    result->ngapIp = yaml::GetIp4(config, "ngapIp");
    result->gtpIp = yaml::GetIp4(config, "gtpIp");

    result->ignoreStreamIds = yaml::GetBool(config, "ignoreStreamIds");
    result->pagingDrx = EPagingDrx::V128;
    result->name = "UERANSIM-gnb-" + std::to_string(result->plmn.mcc) + "-" + std::to_string(result->plmn.mnc) + "-" +
                   std::to_string(result->getGnbId()); // NOTE: Avoid using "/" dir separator character.

    for (auto &amfConfig : yaml::GetSequence(config, "amfConfigs"))
    {
        nr::gnb::GnbAmfConfig c{};
        c.address = yaml::GetIp4(amfConfig, "address");
        c.port = static_cast<uint16_t>(yaml::GetInt32(amfConfig, "port", 1024, 65535));
        result->amfConfigs.push_back(c);
    }

    for (auto &nssai : yaml::GetSequence(config, "slices"))
    {
        SliceSupport s{};
        s.sst = yaml::GetInt32(nssai, "sst", 1, 0xFF);
        if (yaml::HasField(nssai, "sd"))
            s.sd = octet3{yaml::GetInt32(nssai, "sd", 1, 0xFFFFFF)};
        result->nssais.push_back(s);
    }

    return result;
}

static nr::gnb::GnbConfig *GetConfig(const std::string &file)
{
    try
    {
        return ReadConfigYaml(file);
    }
    catch (const std::runtime_error &e)
    {
        std::cerr << "ERROR: " << e.what() << std::endl;
        exit(1);
    }
}

static void ReadOptions(int argc, char **argv, std::string &configFile)
{
    try
    {
        cxxopts::Options options("nr-gnb", "5G-SA gNB implementation | Copyright (c) 2021 Ali Güngör");
        options.add_options()("c,config", "Use specified configuration file for gNB",
                              cxxopts::value<std::string>())("h,help", "Show this help message and exit");

        auto result = options.parse(argc, argv);

        if (result.arguments().empty() || result.count("help"))
        {
            std::cout << options.help() << std::endl;
            exit(0);
        }

        configFile = result["config"].as<std::string>();
    }
    catch (const cxxopts::OptionException &e)
    {
        std::cerr << "ERROR: " << e.what() << std::endl;
        exit(1);
    }
}

int main(int argc, char **argv)
{
    std::cout << cons::Name << std::endl;

    std::string configFile;
    ReadOptions(argc, argv, configFile);

    nr::gnb::GnbConfig *config = GetConfig(configFile);

    auto *gnb = new nr::gnb::GNodeB(config, nullptr);
    gnb->start();

    while (true)
        ::pause();
}