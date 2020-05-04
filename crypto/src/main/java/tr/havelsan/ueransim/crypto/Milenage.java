package tr.havelsan.ueransim.crypto;

import threegpp.milenage.MilenageResult;
import threegpp.milenage.biginteger.BigIntegerBuffer;
import threegpp.milenage.biginteger.BigIntegerBufferFactory;
import threegpp.milenage.cipher.Ciphers;
import tr.havelsan.ueransim.utils.Utils;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Milenage {

    public static byte[] computeRes(String key, String op, String rand, String sqn, String amf, boolean padResResult) {
        return computeRes(
                Utils.hexStringToByteArray(key),
                Utils.hexStringToByteArray(op),
                Utils.hexStringToByteArray(rand),
                Utils.hexStringToByteArray(sqn),
                Utils.hexStringToByteArray(amf),
                padResResult
        );
    }

    public static byte[] computeRes(byte[] key, byte[] op, byte[] rand, byte[] sqn, byte[] amf, boolean padResResult) {
        var milenageBufferFactory = BigIntegerBufferFactory.getInstance();

        var cipher = Ciphers.createRijndaelCipher(key);

        byte[] opc = threegpp.milenage.Milenage.calculateOPc(op, cipher, milenageBufferFactory);

        threegpp.milenage.Milenage<BigIntegerBuffer> milenage = new threegpp.milenage.Milenage<>(opc, cipher, milenageBufferFactory);
        ExecutorService executorService = Executors.newCachedThreadPool();

        Map<MilenageResult, byte[]> result;
        try {
            result = milenage.calculateAll(rand, sqn, amf, executorService);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown();

        var res = result.get(MilenageResult.RES);

        if (padResResult) {
            int padding = (res.length) % 4;
            byte[] paddedRes = new byte[res.length + padding + 2];
            System.arraycopy(res, 0, paddedRes, padding + 2, res.length);
            int resBitLength = (paddedRes.length - 2) * 8;
            paddedRes[0] = (byte) ((resBitLength >> 8) & 0xFF);
            paddedRes[1] = (byte) (resBitLength & 0xFF);
            res = paddedRes;
        }

        return res;
    }
}
