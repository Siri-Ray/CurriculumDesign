package Utils;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import java.util.Arrays;

/**
 * @Author itmei
 * @Date 2024/5/12 14:03
 * @description:
 * @Title: SM3Util
 * @Package PACKAGE_NAME
 */
public class SM3Util {
    /**
     * SM3加密方式之：不提供密钥的方式 SM3加密，返回加密后长度为64位的16进制字符串
     *
     * @param src 明文
     * @return
     */
    public static String encrypt(String src) {
        return ByteUtils.toHexString(getEncryptBySrcByte(src.getBytes()));
    }

    /**
     * 返回长度为32位的加密后的byte数组
     *
     * @param srcByte
     * @return
     */
    public static byte[] getEncryptBySrcByte(byte[] srcByte) {
        SM3Digest sm3 = new SM3Digest();
        sm3.update(srcByte, 0, srcByte.length);
        byte[] encryptByte = new byte[sm3.getDigestSize()];
        sm3.doFinal(encryptByte, 0);
        return encryptByte;
    }

    /**
     * 校验源数据与加密数据是否一致
     *
     * @param src       源数据
     * @param sm3HexStr 16进制的加密数据
     * @return
     * @throws Exception
     */
    public static boolean verify(String src, String sm3HexStr) throws Exception {
        byte[] sm3HashCode = ByteUtils.fromHexString(sm3HexStr);
        byte[] newHashCode = getEncryptBySrcByte(src.getBytes(src));
        return Arrays.equals(newHashCode, sm3HashCode);
    }
}
