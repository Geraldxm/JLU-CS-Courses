package com.huawei.classroom.teacher;

import java.util.List;

import com.huawei.classroom.context.ClassContext;
import com.huawei.classroom.student.entity.Account;
import com.huawei.classroom.student.entity.Flower;
import com.huawei.classroom.student.entity.FlowerStore;
import com.huawei.classroom.student.service.impl.FlowerOwnerServiceImpl;
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
        FlowerOwnerServiceImpl flowerOwnerServiceImpl = new FlowerOwnerServiceImpl();
        List<Flower> result1 = flowerOwnerServiceImpl.getMyFlower(2);
        context.testcase((List<Account> b) -> b.size()==1).score(50).description("有1种花").test(result1);
        FlowerStoreServiceImpl flowerStoreServiceImpl = new FlowerStoreServiceImpl();
        FlowerStore result2 = flowerStoreServiceImpl.getFlowerStore(1);
        context.testcase((FlowerStore b) -> b.getName().equals("小丑鲜花店")).score(50).description("有小丑鲜花店").test(result2);
        context.printResult();
    }
}
