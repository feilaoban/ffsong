package com.flying.demo.generics;

import java.util.List;

/**
 * @Author songfeifei
 * @Date 2022/6/29 14:40
 * @Description 泛型
 * 1、extends也称为上界通配符，就是指定上边界。即泛型中的类必须为当前类的子类或当前类。不能往里存，只能往外取
 * 2、super也称为下界通配符，就是指定下边界。即泛型中的类必须为当前类或者其父类。不影响往里存，但往外取只能放在Object对象里
 * 【注】在使用泛型时，存取元素时用super,获取元素时，用extends
 * 【扩展】通配符<?>和类型参数<T>的区别就在于，对编译器来说所有的T都代表同一种类型，而<?>没有这种约束。
 */
public class GenericsTest {

    /*
    public class Food {}
    public class Fruit extends Food {}
    public class Apple extends Fruit {}
    public class Banana extends Fruit{}
    */

    public void testExtends(List<? extends Fruit> fruits) {
        //fruits.add(new Apple());  报错，不知道fruits中到底存的是什么，可能是Fruit、Apple或Banana
        //fruits.add(new Fruit());  报错，同上
        //fruits.add(new Food());   报错，fruits只能存Fruit及其子类，Fruit为上边界
        Fruit fruit = fruits.get(0);
    }

    public void testSuper(List<? super Fruit> fruitsA) {
        // 理解：fruitsA只能存Fruit及其子类，Fruit及其子类为下边界
        fruitsA.add(new Apple());
        fruitsA.add(new Fruit());
        //fruitsA.add(new Food());  报错，不知道fruits中到底存的是什么，可能是Food、Food的父类或Object
        Object object = fruitsA.get(0);
    }
}
