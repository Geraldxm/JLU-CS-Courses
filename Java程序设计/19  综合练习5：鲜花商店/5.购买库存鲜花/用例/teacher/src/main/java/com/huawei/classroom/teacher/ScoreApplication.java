package com.huawei.classroom.teacher;

import java.util.List;

import com.huawei.classroom.context.ClassContext;
import com.huawei.classroom.student.entity.Flower;
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
        List<Flower> result2 = flowerStoreServiceImpl.getFlowersInstock(0);
        for(Flower flower : result2) {
        context.testcase((Flower b) -> b.getOwnerId()==0).score(10).description("是无ownerid鲜花可以购买").test(flower);
        }
        context.printResult();
    }
}
