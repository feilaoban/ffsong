package com.flying.demo.lambda;

import java.util.function.Consumer;

/**
 * @Author songfeifei
 * @Date 2021/12/10 11:41
 * @Description
 */
public class MyConsumer<String> implements Consumer<String> {
    @Override
    public void accept(String s) {
        System.out.println(s);
    }
}
