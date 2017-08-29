package com.company.common.model;

/**
 * @Description RedisKey前缀
 * @Author ErnestCheng
 * @Date 2017/6/6.
 */
public class RedisKey {

    private RedisKey(){
        throw new IllegalStateException("Utility class");
    }

    /**
     * expamle :shop_product_id:16
     */
    public  static String product="shop_product_id";
    /**
     * expamle:shop_user_id:3
     */
    public static String user="shop_user_id";
}
