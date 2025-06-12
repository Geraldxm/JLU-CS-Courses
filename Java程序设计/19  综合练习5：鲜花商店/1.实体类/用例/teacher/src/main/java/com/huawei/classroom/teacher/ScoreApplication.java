package com.huawei.classroom.teacher;

import java.lang.reflect.Method;
import java.util.ArrayList;

import com.huawei.classroom.context.ClassContext;
import com.huawei.classroom.student.entity.Flower;
import com.huawei.classroom.student.entity.FlowerOwner;
import com.huawei.classroom.student.entity.FlowerStore;

/**
 * 用例执行入口程序。
 */
public class ScoreApplication {

    /**
     * 用例执行入口。
     * @param args
     */
    public static void main(String[] args) {
        ClassContext context = new ClassContext();
        ArrayList<String> methodNames = new ArrayList<String>();
    	Flower flower =  new Flower();
		Class<?> clazz = flower.getClass();
		//获取本类的所有方法，存放入数组
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			methodNames.add(method.getName());
		}
        context.testcase((ArrayList<String> b) -> b.contains("getId")).score(5).description("方法存在").test(methodNames);
        context.testcase((ArrayList<String> b) -> b.contains("setId")).score(5).description("方法存在").test(methodNames);
        context.testcase((ArrayList<String> b) -> b.contains("getName")).score(5).description("方法存在").test(methodNames);
        context.testcase((ArrayList<String> b) -> b.contains("setName")).score(5).description("方法存在").test(methodNames);
        context.testcase((ArrayList<String> b) -> b.contains("getTypeName")).score(5).description("方法存在").test(methodNames);
        context.testcase((ArrayList<String> b) -> b.contains("setTypeName")).score(5).description("方法存在").test(methodNames);
        context.testcase((ArrayList<String> b) -> b.contains("getOwnerId")).score(5).description("方法存在").test(methodNames);
        context.testcase((ArrayList<String> b) -> b.contains("setOwnerId")).score(5).description("方法存在").test(methodNames);
        context.testcase((ArrayList<String> b) -> b.contains("getStoreId")).score(5).description("方法存在").test(methodNames);
        context.testcase((ArrayList<String> b) -> b.contains("setStoreId")).score(5).description("方法存在").test(methodNames);
        context.testcase((ArrayList<String> b) -> b.contains("getPrice")).score(5).description("方法存在").test(methodNames);
        context.testcase((ArrayList<String> b) -> b.contains("setPrice")).score(5).description("方法存在").test(methodNames);
        ArrayList<String> methodNames1 = new ArrayList<String>();
    	FlowerOwner FlowerOwner = new FlowerOwner();
		Class<?> clazz1 = FlowerOwner.getClass();
		//获取本类的所有方法，存放入数组
		Method[] methods1 = clazz1.getDeclaredMethods();
		for (Method method : methods1) {
			methodNames1.add(method.getName());
		}

        context.testcase((ArrayList<String> b) -> b.contains("getName")).score(5).description("方法存在").test(methodNames1);
        context.testcase((ArrayList<String> b) -> b.contains("setName")).score(5).description("方法存在").test(methodNames1);
        context.testcase((ArrayList<String> b) -> b.contains("getPassword")).score(5).description("方法存在").test(methodNames1);
        context.testcase((ArrayList<String> b) -> b.contains("setPassword")).score(5).description("方法存在").test(methodNames1);
        context.testcase((ArrayList<String> b) -> b.contains("getMoney")).score(5).description("方法存在").test(methodNames1);
        context.testcase((ArrayList<String> b) -> b.contains("setMoney")).score(5).description("方法存在").test(methodNames1);
        ArrayList<String> methodNames2 = new ArrayList<String>();
        FlowerStore flowerStore = new FlowerStore();
		Class<?> clazz2 = flowerStore.getClass();
		//获取本类的所有方法，存放入数组
		Method[] methods2 = clazz2.getDeclaredMethods();
		for (Method method : methods2) {
			methodNames2.add(method.getName());
		}
        context.testcase((ArrayList<String> b) -> b.contains("getName")).score(5).description("方法存在").test(methodNames2);
        context.testcase((ArrayList<String> b) -> b.contains("setName")).score(5).description("方法存在").test(methodNames2);
        context.printResult();
    }
}