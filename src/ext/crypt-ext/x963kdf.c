/* X963kdf.h -- X9.63 Key Derivation Function
2023-03-22 : Stephane G. : Public domain */

#include "x963kdf.h"
// STEPHANE
#include<stdio.h>

// function X963KDF(sharedSecret, sharedInfo, keySize){
//   var maxCount = Math.ceil(keySize/digestLen);
//   var result = Buffer.allocUnsafe(0);
//   for (var count = 1; count < maxCount + 1; count++){
//       var counter = Buffer.allocUnsafe(4);
//       counter.writeUInt32BE(count, 0);
//       var current = Buffer.concat([sharedSecret, counter, sharedInfo]);
//       var hash = crypto.createHash(digest).update(current).digest();
//       result = Buffer.concat([result, hash]);
//   }
  
//   return result.slice(0, keySize);
// }

void x963kdf(unsigned char *output, const unsigned char *sharedSecret, const unsigned char *sharedInfo, size_t keySize) 
{
    int maxCount = ceil(keySize/SHA256_DIGEST_SIZE);
    uint8_t result[keySize];
    uint8_t counterBuf[4];
    
    for (int count = 1; count < maxCount + 1; count++)
    {
        sha256_t ss;
        uint8_t hash[SHA256_DIGEST_SIZE];
        
        sha256_init(&ss);
        sha256_update(&ss, sharedSecret, 32);
        // To be improved in order to deal with Little and Big Endian
        counterBuf[0] = (uint8_t) ((count >> 24) & 0xff);
        counterBuf[1] = (uint8_t) ((count >> 16) & 0xff);
        counterBuf[2] = (uint8_t) ((count >> 8) & 0xff);
        counterBuf[3] = (uint8_t) ((count >> 0) & 0xff);
        sha256_update(&ss, counterBuf, 4);
        sha256_update(&ss, sharedInfo, 32);
        sha256_final(&ss, hash);
        memcpy(result + (count -1)*SHA256_DIGEST_SIZE, hash, SHA256_DIGEST_SIZE);
    }    
    memcpy(output, result, keySize);
}