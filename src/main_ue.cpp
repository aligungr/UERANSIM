//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include <common.hpp>
#include <constants.hpp>
#include <cxxopts/cxxopts.hpp>
#include <iostream>
#include <ue.hpp>
#include <unistd.h>
#include <yaml-cpp/yaml.h>
#include <yaml_utils.hpp>

static nr::ue::UeConfig *ReadConfigYaml(const std::string &file, bool configureRouting)
{
    auto *result = new nr::ue::UeConfig();
    auto config = YAML::LoadFile(file);

    result->plmn.mcc = yaml::GetInt32(config, "mcc", 1, 999);
    yaml::GetString(config, "mcc", 3, 3);
    result->plmn.mnc = yaml::GetInt32(config, "mnc", 1, 999);
    result->plmn.isLongMnc = yaml::GetString(config, "mnc", 2, 3).size() == 3;

    result->imei = yaml::GetString(config, "imei", 15, 15);

    for (auto &gnbSearchItem : yaml::GetSequence(config, "gnbSearchList"))
        result->gnbSearchList.push_back(gnbSearchItem.as<std::string>());

    for (auto &nssai : yaml::GetSequence(config, "slices"))
    {
        SliceSupport s{};
        s.sst = yaml::GetInt32(nssai, "sst", 1, 0xFF);
        if (yaml::HasField(nssai, "sd"))
            s.sd = octet3{yaml::GetInt32(nssai, "sd", 1, 0xFFFFFF)};
        result->nssais.push_back(s);
    }

    result->key = OctetString::FromHex(yaml::GetString(config, "key", 32, 32));
    result->opC = OctetString::FromHex(yaml::GetString(config, "op", 32, 32));
    result->amf = OctetString::FromHex(yaml::GetString(config, "amf", 4, 4));

    result->emulationMode = true;
    result->configureRouting = configureRouting;

    if (yaml::HasField(config, "supi"))
        result->supi = Supi::Parse(yaml::GetString(config, "supi"));

    yaml::AssertHasField(config, "integrity");
    yaml::AssertHasField(config, "ciphering");

    result->supportedAlgs.nia1 = yaml::GetBool(config["integrity"], "IA1");
    result->supportedAlgs.nia2 = yaml::GetBool(config["integrity"], "IA2");
    result->supportedAlgs.nia3 = yaml::GetBool(config["integrity"], "IA3");
    result->supportedAlgs.nea1 = yaml::GetBool(config["ciphering"], "EA1");
    result->supportedAlgs.nea2 = yaml::GetBool(config["ciphering"], "EA2");
    result->supportedAlgs.nea3 = yaml::GetBool(config["ciphering"], "EA3");

    std::string opType = yaml::GetString(config, "opType");
    if (opType == "OP")
        result->opType = nr::ue::OpType::OP;
    else if (opType == "OPC")
        result->opType = nr::ue::OpType::OPC;
    else
        throw std::runtime_error("Invalid OP type: " + opType);

    for (auto &sess : yaml::GetSequence(config, "sessions"))
    {
        nr::ue::SessionConfig s{};

        if (yaml::HasField(sess, "apn"))
            s.apn = yaml::GetString(sess, "apn");
        if (yaml::HasField(sess, "slice"))
        {
            auto slice = sess["slice"];
            s.sNssai = SliceSupport{};
            s.sNssai->sst = yaml::GetInt32(slice, "sst", 1, 0xFF);
            if (yaml::HasField(slice, "sd"))
                s.sNssai->sd = octet3{yaml::GetInt32(slice, "sd", 1, 0xFFFFFF)};
        }

        std::string type = yaml::GetString(sess, "type");
        if (type == "IPv4")
            s.type = nas::EPduSessionType::IPV4;
        else if (type == "IPv6")
            s.type = nas::EPduSessionType::IPV6;
        else if (type == "IPv4v6")
            s.type = nas::EPduSessionType::IPV4V6;
        else if (type == "Ethernet")
            s.type = nas::EPduSessionType::ETHERNET;
        else if (type == "Unstructured")
            s.type = nas::EPduSessionType::UNSTRUCTURED;
        else
            throw std::runtime_error("PDU session type: " + type);

        result->initSessions.push_back(s);
    }

    return result;
}

static nr::ue::UeConfig *GetConfig(const std::string &file, bool configureRouting)
{
    try
    {
        return ReadConfigYaml(file, configureRouting);
    }
    catch (const std::runtime_error &e)
    {
        std::cerr << "ERROR: " << e.what() << std::endl;
        exit(1);
    }
}

static void ReadOptions(int argc, char **argv, std::string &configFile, bool &noRoutingConfigs)
{
    try
    {
        cxxopts::Options options("nr-ue", "5G-SA UE implementation | Copyright (c) 2021 UERANSIM");
        options.add_options()("c,config", "Use specified configuration file for UE", cxxopts::value<std::string>())(
            "r,no-routing-config",
            "Do not auto configure routing for UE TUN interface")("h,help", "Show this help message and exit");

        auto result = options.parse(argc, argv);

        if (result.arguments().empty() || result.count("help"))
        {
            std::cout << options.help() << std::endl;
            exit(0);
        }

        configFile = result["config"].as<std::string>();
        noRoutingConfigs = result.count("no-routing-config");
    }
    catch (const cxxopts::OptionException &e)
    {
        std::cerr << "ERROR: " << e.what() << std::endl;
        exit(1);
    }
    catch (const std::runtime_error &e)
    {
        std::cerr << "ERROR: " << e.what() << std::endl;
        exit(1);
    }
}

int main(int argc, char **argv)
{
    std::string configFile;
    bool noRoutingConfigs;

    ReadOptions(argc, argv, configFile, noRoutingConfigs);

    nr::ue::UeConfig *config = GetConfig(configFile, !noRoutingConfigs);

    auto *ue = new nr::ue::UserEquipment(config, nullptr);
    ue->start();

    while (true)
        ::pause();
}