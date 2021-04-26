//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <ue/types.hpp>

namespace nr::ue::keys
{

/**
 * Derives SEAF and AMF keys
 */
void DeriveKeysSeafAmf(const UeConfig &ueConfig, const Plmn &currentPlmn, NasSecurityContext &nasSecurityContext);

/**
 * Derives NAS keys
 */
void DeriveNasKeys(NasSecurityContext &securityContext);

/**
 * Constructs SNN (Serving Network Name) according to given PLMN as specified in 3GPP TS 24.501
 */
std::string ConstructServingNetworkName(const Plmn &plmn);

/**
 * Derives kAMF to kAMF' in mobility 33.501/A.13
 */
OctetString DeriveAmfPrimeInMobility(bool isUplink, const NasCount &count, const OctetString &kAmf);

/**
 * Calculates K_AUSF for 5G-AKA according to given parameters as specified in 3GPP TS 33.501 Annex A.2
 */
OctetString CalculateKAusfFor5gAka(const OctetString &ck, const OctetString &ik, const std::string &snn,
                                   const OctetString &sqnXorAk);

/**
 * Calculates CK' and IK' according to given parameters as specified in 3GPP TS 33.501 Annex A.3
 */
std::pair<OctetString, OctetString> CalculateCkPrimeIkPrime(const OctetString &ck, const OctetString &ik,
                                                            const std::string &snn, const OctetString &sqnXorAk);

/**
 * Calculates mk for EAP-AKA' according to given parameters as specified in RFC 5448.
 */
OctetString CalculateMk(const OctetString &ckPrime, const OctetString &ikPrime, const Supi &supiIdentity);

/**
 * Calculates MAC for EAP-AKA' according to given parameters.
 */
OctetString CalculateMacForEapAkaPrime(const OctetString &kaut, const eap::EapAkaPrime &message);

/**
 * Calculates K_AUSF for EAP-AKA' according to given parameters as specified in 3GPP TS 33.501 Annex F.
 */
OctetString CalculateKAusfForEapAkaPrime(const OctetString &mk);

/**
 * Calculates RES* according to given parameters as specified in 3GPP TS 33.501
 *
 * @param key  The input key KEY shall be equal to the concatenation CK || IK of CK and IK.
 * @param snn  The serving network name shall be constructed as specified in the TS.
 * @param rand RAND value
 * @param res  RES value
 */
OctetString CalculateResStar(const OctetString &key, const std::string &snn, const OctetString &rand,
                             const OctetString &res);

/*
 * Calculates AUTS according to the given parameters
 */
OctetString CalculateAuts(const OctetString &sqn, const OctetString &ak, const OctetString &macS);

} // namespace nr::ue::keys
