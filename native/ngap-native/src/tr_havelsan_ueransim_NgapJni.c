#include "tr_havelsan_ueransim_NgapJni.h"

#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     tr_havelsan_ueransim_NgapJni
 * Method:    sum
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_tr_havelsan_ueransim_NgapJni_sum
  (JNIEnv *env, jclass cls, jint x, jint y) {
      return x + y;
  }

#ifdef __cplusplus
}
#endif

void test() {
  int x = 34;
}