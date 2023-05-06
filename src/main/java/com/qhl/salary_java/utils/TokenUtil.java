package com.qhl.salary_java.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;
import java.util.Random;

/**
 * 2019-05-15
 *
 * @author noxus
 */
public class TokenUtil {

    /**
     * 生成token的方式  账户名 + 账户类型 + 10位随机字符 + 时间戳， 保证不重复
     *
     * @param account
     * @return
     */
    public static String createToken(String account, int userType) {
        byte[] randomBytes = DigestUtils.sha1(account + userType + getRandomString(10) + System.currentTimeMillis());
        return Base64.encodeBase64String(randomBytes);
    }
    
    /**
     * 生成uuid的方式 uid+当前时间的年月日时分秒
     * @param nextId
     * @return
     */
    public static String createUuid(Long uid,String date){
    	byte[] randomBytes = DigestUtils.sha1(uid + date);
        return Base64.encodeBase64String(randomBytes);
    }
    
    public static void main(String[] args) {
    	String createUuid1 = createUuid(230L, DateFormatUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
    	String createUuid2 = createUuid(230L, DateFormatUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
    	System.out.println(createUuid1);
    	System.out.println(createUuid2);
	}
    
    public static String createToken(Long nextId){
    	String val = "";
		Random random = new Random();
		for (int i = 0; i < 28; i++) {//定义随机数位数
			// 输出字母还是数字
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 字符串
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 取得大写字母还是小写字母
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val+String.valueOf(nextId);
    }
    
    public static String createToken(String publicKey, String privateKey) {
        byte[] randomBytes = DigestUtils.sha1(publicKey + publicKey + getRandomString(10) + System.currentTimeMillis());
        return Base64.encodeBase64String(randomBytes);
    }

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(52);// [0,51)
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

}
