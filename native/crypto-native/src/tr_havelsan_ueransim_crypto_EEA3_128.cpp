#include <jni.h>
#include "eea3_128.h"
#include "utils.h"

extern "C" JNIEXPORT jbyteArray JNICALL Java_tr_havelsan_ueransim_crypto_EEA3_1128_eea3(JNIEnv *env, jclass cls, jlong count, jint bearer, jboolean direction, jbyteArray message, jint bitLength, jbyteArray key)
{
    jsize mWordLen;

    auto CK = Utils::jbyteArrayToUint8Array(env, key, 1, nullptr);
    auto M = Utils::jbyteArrayToUint32Array(env, message, &mWordLen);
    auto C = new uint32_t[mWordLen];

    EEA3_128::EEA3(CK, static_cast<uint32_t>(count), static_cast<uint32_t>(bearer), direction, static_cast<uint32_t>(bitLength), M, C);

    delete[] CK;
    delete[] M;

    auto res = Utils::uint32ArrayToJbyteArray(env, C, mWordLen);
    delete[] C;
    return res;
}
