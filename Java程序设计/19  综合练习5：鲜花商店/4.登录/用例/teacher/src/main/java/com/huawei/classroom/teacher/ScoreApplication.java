package com.huawei.classroom.teacher;

import com.huawei.classroom.context.ClassContext;
import com.huawei.classroom.student.entity.FlowerStore;
import com.huawei.classroom.student.service.impl.FlowerStoreServiceImpl;

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
        FlowerStoreServiceImpl flowerStoreServiceImpl = new FlowerStoreServiceImpl();
        FlowerStore result2 = flowerStoreServiceImpl.subLogin("爱慕鲜花店", "123");
        context.testcase((FlowerStore b) -> b.getName().equals("爱慕鲜花店")).score(100).description("爱慕鲜花店login成功").test(result2);
        context.printResult();
    }
}
