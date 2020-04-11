#include <jni.h>
#include "uea2.h"
#include "utils.h"

extern "C" JNIEXPORT jbyteArray JNICALL Java_tr_havelsan_ueransim_crypto_UEA2_uea2(JNIEnv *env, jclass cls, jlong count, jint bearer, jboolean direction, jbyteArray message, jint bitLength, jbyteArray key)
{
    jsize dataLen;

    uint8_t *K = Utils::jbyteArrayToUint8Array(env, key, 1, nullptr);
    uint8_t *M = Utils::jbyteArrayToUint8Array(env, message, 1, &dataLen);

    UEA2::f8(K, static_cast<uint32_t>(count), static_cast<uint32_t>(bearer), static_cast<uint32_t>(direction), M, static_cast<uint32_t>(bitLength));

    delete[] K;

    jbyteArray jarr = Utils::byteArrayToJByteArray(env, M, dataLen);
    delete[] M;

    return jarr;
}