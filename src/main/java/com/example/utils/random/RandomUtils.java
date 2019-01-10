package com.example.utils.random;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Description 随机数工具类
 * @Author blake
 * @Date 2018/12/5 上午11:28
 * @Version 1.0
 */
public class RandomUtils {

    /**
     * 返回长度为[strLength]的随机数，在前面补0
     */
    public static String getFixLengthString(int strLength) {

        Random rm = new Random();

        // 获得随机数
        double randomSn = (1 + rm.nextDouble()) * Math.pow(10, strLength);

        // 将获得的获得随机数转化为字符串
        String randomSnString = String.valueOf(randomSn);

        // 返回固定的长度的随机数
        return randomSnString.substring(2, strLength + 2);
    }

    /**
     * 随机生成6位长度验证码
     */
    public static String getRandomCode() {
        final int max = 999999;
        final int min = 1;
        return String.format("%06d", new Random().nextInt(max - min + 1) + min);
    }

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        int i = 0;
        while (i < Math.pow(10, 6)) {

            String randomSnString = RandomUtils.getFixLengthString(10);

            if (list.contains(randomSnString)) {
                System.out.println("============ 出现重复 ============");
            }

            list.add(randomSnString);

            System.out.println(randomSnString);
            i++;
        }
    }

}
