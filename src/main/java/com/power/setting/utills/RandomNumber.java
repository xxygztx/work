package com.power.setting.utills;

import java.util.Random;
import java.util.UUID;

public class RandomNumber {
    /**
     * 生成一个32位的随机数，不会重复
     * @return
     */
    public static String getUuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
    public static int Num(){
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Random rand = new Random();
        for (int i = 10; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = array[index];
            array[index] = array[i - 1];
            array[i - 1] = tmp;
        }
        int result = 0;
        for (int i = 0; i < 6; i++) {
            result = result * 10 + array[i];
        }
        if (String.valueOf(result).length() == 6) {
            return result;
        } else {
            return Num();
        }
    }
    public static String getNum(){
        return String.valueOf(RandomNumber.Num());
    }
}
