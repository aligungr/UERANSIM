//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "keys.hpp"
#include <lib/crypt/crypt.hpp>
#include <stdexcept>

static const int N_NAS_enc_alg = 0x01;
static const int N_NAS_int_alg = 0x02;
static const int N_RRC_enc_alg = 0x03;
static const int N_RRC_int_alg = 0x04;
static const int N_UP_enc_alg = 0x05;
static const int N_UP_int_alg = 0x06;

namespace nr::ue::keys
{

void DeriveKeysSeafAmf(const UeConfig &ueConfig, const Plmn &currentPlmn, NasSecurityContext &nasSecurityContext)
{
    auto &keys = nasSecurityContext.keys;
    std::string snn = ConstructServingNetworkName(currentPlmn);

    OctetString s1[1];
    s1[0] = crypto::EncodeKdfString(snn);

    OctetString s2[2];
    s2[0] = crypto::EncodeKdfString(ueConfig.supi->value);
    s2[1] = keys.abba.copy();

    keys.kSeaf = crypto::CalculateKdfKey(keys.kAusf, 0x6C, s1, 1);
    keys.kAmf = crypto::CalculateKdfKey(keys.kSeaf, 0x6D, s2, 2);
}

void DeriveNasKeys(NasSecurityContext &securityContext)
{
    OctetString s1[2];
    s1[0] = OctetString::FromOctet(N_NAS_enc_alg);
    s1[1] = OctetString::FromOctet((int)securityContext.ciphering);

    OctetString s2[2];
    s2[0] = OctetString::FromOctet(N_NAS_int_alg);
    s2[1] = OctetString::FromOctet((int)securityContext.integrity);

    auto kdfEnc = crypto::CalculateKdfKey(securityContext.keys.kAmf, 0x69, s1, 2);
    auto kdfInt = crypto::CalculateKdfKey(securityContext.keys.kAmf, 0x69, s2, 2);

    securityContext.keys.kNasEnc = kdfEnc.subCopy(16, 16);
    securityContext.keys.kNasInt = kdfInt.subCopy(16, 16);
}

std::string ConstructServingNetworkName(const Plmn &plmn)
{
    char buffer[40] = {0};
    int r = sprintf(buffer, "5G:mnc%03d.mcc%03d.3gppnetwork.org", plmn.mnc, plmn.mcc);
    if (r != 32)
        throw std::runtime_error("SNN construction failure");
    return std::string{buffer};
}

OctetString CalculateKAusfFor5gAka(const OctetString &ck, const OctetString &ik, const std::string &snn,
                                   const OctetString &sqnXorAk)
{
    OctetString key = OctetString::Concat(ck, ik);
    OctetString s[2];
    s[0] = crypto::EncodeKdfString(snn);
    s[1] = sqnXorAk.copy();
    return crypto::CalculateKdfKey(key, 0x6A, s, 2);
}

std::pair<OctetString, OctetString> CalculateCkPrimeIkPrime(const OctetString &ck, const OctetString &ik,
                                                            const std::string &snn, const OctetString &sqnXorAk)
{
    OctetString key = OctetString::Concat(ck, ik);
    OctetString s[2];
    s[0] = crypto::EncodeKdfString(snn);
    s[1] = sqnXorAk.copy();

    auto res = crypto::CalculateKdfKey(key, 0x20, s, 2);

    std::pair<OctetString, OctetString> ckIk;
    ckIk.first = res.subCopy(0, ck.length());
    ckIk.second = res.subCopy(ck.length());
    return ckIk;
}

OctetString CalculateMk(const OctetString &ckPrime, const OctetString &ikPrime, const Supi &supiIdentity)
{
    OctetString key = OctetString::Concat(ikPrime, ckPrime);
    OctetString input = OctetString::FromAscii("EAP-AKA'" + supiIdentity.value);

    // Calculating the 208-octet output
    return crypto::CalculatePrfPrime(key, input, 208);
}

OctetString CalculateMacForEapAkaPrime(const OctetString &kaut, const eap::EapAkaPrime &message)
{
    // Create a copy of the EAP message
    eap::EapAkaPrime copy{message.code, message.id, message.subType};

    // Deep copy each attribute
    message.attributes.forEachEntryOrdered(
        [&copy](eap::EAttributeType attr, const OctetString &v) { copy.attributes.putRawAttribute(attr, v.copy()); });

    // Except the MAC field
    copy.attributes.replaceMac(OctetString::FromSpare(16));

    OctetString input{};
    eap::EncodeEapPdu(input, copy);

    auto sha = crypto::HmacSha256(kaut, input);
    return sha.subCopy(0, 16);
}

OctetString CalculateKAusfForEapAkaPrime(const OctetString &mk)
{
    // Octets [144...207] are EMSK
    auto emsk = mk.subCopy(144);
    // The most significant 256 bits of EMSK is K_AUSF
    return emsk.subCopy(0, 32);
}

OctetString CalculateResStar(const OctetString &key, const std::string &snn, const OctetString &rand,
                             const OctetString &res)
{
    OctetString params[3];
    params[0] = crypto::EncodeKdfString(snn);
    params[1] = rand.copy();
    params[2] = res.copy();

    auto output = crypto::CalculateKdfKey(key, 0x6B, params, 3);

    // The (X)RES* is identified with the 128 least significant bits of the output of the KDF.
    return output.subCopy(output.length() - 16);
}

OctetString DeriveAmfPrimeInMobility(bool isUplink, const NasCount &count, const OctetString &kAmf)
{
    OctetString params[2];
    params[0] = OctetString::FromOctet(isUplink ? 0x00 : 0x01);
    params[1] = OctetString::FromOctet4(count.toOctet4());

    return crypto::CalculateKdfKey(kAmf, 0x72, params, 2);
}

OctetString CalculateAuts(const OctetString &sqn, const OctetString &ak, const OctetString &macS)
{
    OctetString auts = OctetString::Xor(sqn, ak);
    auts.append(macS);
    return auts;
}

} // namespace nr::ue::keys