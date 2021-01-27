//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

// Ported from org.bouncycastle.crypto.engines.AesEngine

#include <cstdint>

class AESEngine
{
    int ROUNDS{};
    int WorkingKey[11][4] = {0};
    int C0{}, C1{}, C2{}, C3{};
    bool forEncryption{};
    uint8_t s[256] = {0};

  public:
    AESEngine();
    void init(bool forEncryption, const uint8_t *key);
    int processBlock(uint8_t *in, int inOff, int inLen, uint8_t *out, int outOff, int outLen);
    void reset();

  private:
    void generateWorkingKey(const uint8_t *key, bool forEncryption);
    void unpackBlock(const uint8_t *bytes, int off);
    void packBlock(uint8_t *bytes, int off) const;
    void encryptBlock(int KW[11][4]);
    void decryptBlock(int KW[11][4]);
};