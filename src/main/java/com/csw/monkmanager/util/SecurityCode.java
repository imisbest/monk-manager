package com.csw.monkmanager.util;

import java.util.Arrays;


public class SecurityCode {

    public static String getSecurityCode() {
        return getSecurityCode(false);
    }




    private static String getSecurityCode(boolean isCanRepeat) {

        char[] codes = {'2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'd',
                'e', 'f', 'g', 'h', 'i', 'm', 'n', 'q', 'r', 't', 'y', 'A',
                'B', 'D', 'E', 'F', 'G', 'H', 'I', 'L', 'M', 'N', 'Q', 'R',
                'T', 'Y'};
        codes = Arrays.copyOfRange(codes, 0, 36);

        int n = codes.length;

        if (4 > n && !isCanRepeat) {
            throw new RuntimeException(String.format(
                    "����SecurityCode.getSecurityCode(%1$s,%2$s,%3$s)�����쳣��"
                            + "��isCanRepeatΪ%3$sʱ���������%1$s���ܴ���%4$s",
                    4, SecurityCodeLevel.Medium, false, n));
        }

        char[] result = new char[4];

        if (isCanRepeat) {
            for (int i = 0; i < result.length; i++) {

                int r = (int) (Math.random() * n);

                result[i] = codes[r];
            }
        } else {
            for (int i = 0; i < result.length; i++) {

                int r = (int) (Math.random() * n);

                result[i] = codes[r];

                codes[r] = codes[n - 1];
                n--;
            }
        }
        return String.valueOf(result);
    }

    public static void main(String[] args) {
        System.out.println(SecurityCode.getSecurityCode());
    }

    /**
     * ��֤���Ѷȼ��� Simple-���� Medium-���ֺ�Сд��ĸ Hard-���ֺʹ�Сд��ĸ
     */
    public enum SecurityCodeLevel {
        Simple, Medium, Hard
    }
}