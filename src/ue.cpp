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
#include <iostream>
#include <ue/ue.hpp>
#include <unistd.h>
#include <yaml-cpp/yaml.h>
#include <utils/yaml_utils.hpp>

static nr::ue::UeConfig *ReadConfigYaml(const std::string &file, bool configureRouting, bool prefixLogger)
{
    auto *result = new nr::ue::UeConfig();
    auto config = YAML::LoadFile(file);

    result->plmn.mcc = yaml::GetInt32(config, "mcc", 1, 999);
    yaml::GetString(config, "mcc", 3, 3);
    result->plmn.mnc = yaml::GetInt32(config, "mnc", 0, 999);
    result->plmn.isLongMnc = yaml::GetString(config, "mnc", 2, 3).size() == 3;

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
    result->prefixLogger = prefixLogger;

    if (yaml::HasField(config, "supi"))
        result->supi = Supi::Parse(yaml::GetString(config, "supi"));
    if (yaml::HasField(config, "imei"))
        result->imei = yaml::GetString(config, "imei", 15, 15);
    if (yaml::HasField(config, "imeiSv"))
        result->imeiSv = yaml::GetString(config, "imeiSv", 16, 16);

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

    if (yaml::HasField(config, "sessions"))
    {
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
                throw std::runtime_error("Invalid PDU session type: " + type);

            result->initSessions.push_back(s);
        }
    }

    return result;
}

static nr::ue::UeConfig *GetConfig(const std::string &file, bool configureRouting, bool prefixLogger)
{
    try
    {
        return ReadConfigYaml(file, configureRouting, prefixLogger);
    }
    catch (const std::runtime_error &e)
    {
        std::cerr << "ERROR: " << e.what() << std::endl;
        exit(1);
    }
}

static void ReadOptions(int argc, char **argv, std::string &configFile, bool &noRoutingConfigs, std::string &imsi,
                        int &count)
{
    try
    {
        cxxopts::Options options("nr-ue", "5G-SA UE implementation | Copyright (c) 2021 Ali Güngör");
        options.add_options()("c,config", "Use specified configuration file for UE", cxxopts::value<std::string>())(
            "i,imsi", "Use specified IMSI number instead of provided one", cxxopts::value<std::string>())(
            "n,num-of-UE", "Create specified number of UEs starting from the given IMSI",
            cxxopts::value<int>())("r,no-routing-config", "Do not auto configure routing for UE TUN interface")(
            "h,help", "Show this help message and exit");

        auto result = options.parse(argc, argv);

        if (result.arguments().empty() || result.count("help"))
        {
            std::cout << options.help() << std::endl;
            exit(0);
        }

        configFile = result["config"].as<std::string>();
        noRoutingConfigs = result.count("no-routing-config");

        if (result.count("num-of-UE"))
        {
            count = result["num-of-UE"].as<int>();
            if (count <= 0)
                throw std::runtime_error("Invalid number of UEs");
            if (count > 512)
                throw std::runtime_error("Number of UEs is too big");
        }
        else
        {
            count = 1;
        }

        imsi = "";

        if (result.count("imsi"))
        {
            imsi = result["imsi"].as<std::string>();
            Supi::Parse("imsi-" + imsi); // validate the string by parsing
        }
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

std::string LargeSum(std::string a, std::string b)
{
    if (a.length() > b.length())
        std::swap(a, b);

    std::string str;
    int n1 = a.length(), n2 = b.length();

    reverse(a.begin(), a.end());
    reverse(b.begin(), b.end());

    int carry = 0;
    for (int i = 0; i < n1; i++)
    {
        int sum = ((a[i] - '0') + (b[i] - '0') + carry);
        str.push_back(sum % 10 + '0');
        carry = sum / 10;
    }
    for (int i = n1; i < n2; i++)
    {
        int sum = ((b[i] - '0') + carry);
        str.push_back(sum % 10 + '0');
        carry = sum / 10;
    }
    if (carry)
        throw std::runtime_error("UE serial number overflow");
    reverse(str.begin(), str.end());
    return str;
}

static void IncrementNumber(std::string &s, int delta)
{
    s = LargeSum(s, std::to_string(delta));
}

nr::ue::UeConfig *GetConfigByUe(nr::ue::UeConfig *refConfig, int ueIndex)
{
    auto *c = new nr::ue::UeConfig();
    c->emulationMode = refConfig->emulationMode;
    c->key = refConfig->key.copy();
    c->opC = refConfig->opC.copy();
    c->opType = refConfig->opType;
    c->amf = refConfig->amf.copy();
    c->imei = refConfig->imei;
    c->imeiSv = refConfig->imeiSv;
    c->supi = refConfig->supi;
    c->plmn = refConfig->plmn;
    c->nssais = refConfig->nssais;
    c->supportedAlgs = refConfig->supportedAlgs;
    c->gnbSearchList = refConfig->gnbSearchList;
    c->initSessions = refConfig->initSessions;
    c->configureRouting = refConfig->configureRouting;
    c->prefixLogger = refConfig->prefixLogger;

    if (c->supi.has_value())
        IncrementNumber(c->supi->value, ueIndex);
    if (c->imei.has_value())
        IncrementNumber(*c->imei, ueIndex);
    if (c->imeiSv.has_value())
        IncrementNumber(*c->imeiSv, ueIndex);

    return c;
}

int main(int argc, char **argv)
{
    std::cout << cons::Name << std::endl;

    std::string configFile, imsi;
    bool noRoutingConfigs;
    int count;

    ReadOptions(argc, argv, configFile, noRoutingConfigs, imsi, count);

    // If we have multiple UEs in the same process, then log names should be separated.
    bool prefixLogger = count > 1;

    nr::ue::UeConfig *refConfig = GetConfig(configFile, !noRoutingConfigs, prefixLogger);
    if (imsi.length() > 0)
        refConfig->supi = Supi::Parse("imsi-" + imsi);

    for (int i = 0; i < count; i++)
    {
        auto *ue = new nr::ue::UserEquipment(GetConfigByUe(refConfig, i), nullptr);
        ue->start();
    }

    while (true)
        ::pause();
}