#include "tr_havelsan_ueransim_crypto_UIA2.h"
#include "utils.h"
#include "uea2.h"

extern "C" JNIEXPORT jint JNICALL Java_tr_havelsan_ueransim_crypto_UIA2_computeMac(JNIEnv *env, jclass cls, jlong count, jlong fresh, jboolean direction, jbyteArray message, jint bitLength, jbyteArray key)
{
    uint8_t *K = Utils::jbyteArrayToUint8Array(env, key, 1, nullptr);
    uint8_t *M = Utils::jbyteArrayToUint8Array(env, message, 1, nullptr);

    uint32_t mac = UEA2::f9(K, static_cast<uint32_t>(count), static_cast<uint32_t>(fresh), static_cast<uint32_t>(direction), M, bitLength);

    delete[] K;
    delete[] M;

    return static_cast<jint>(mac);
}
