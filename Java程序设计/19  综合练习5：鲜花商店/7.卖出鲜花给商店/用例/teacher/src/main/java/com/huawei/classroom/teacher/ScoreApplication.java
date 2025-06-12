package com.huawei.classroom.teacher;

import java.util.List;

import com.huawei.classroom.context.ClassContext;
import com.huawei.classroom.student.dao.BaseDao;
import com.huawei.classroom.student.dao.impl.FlowerDaoImpl;
import com.huawei.classroom.student.dao.impl.FlowerOwnerDaoImpl;
import com.huawei.classroom.student.dao.impl.FlowerStoreDaoImpl;
import com.huawei.classroom.student.entity.Flower;
import com.huawei.classroom.student.entity.FlowerOwner;
import com.huawei.classroom.student.entity.FlowerStore;
import com.huawei.classroom.student.service.impl.FlowerOwnerServiceImpl;

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
        FlowerStoreDaoImpl flowerStoreDaoImpl = new FlowerStoreDaoImpl();
        List<FlowerStore> result2 = flowerStoreDaoImpl.getAllStore();
        context.testcase((List<FlowerStore> b) -> b.size()==3).score(25).description("有3个店").test(result2);
        //测试卖出鲜花给商店
        FlowerOwnerServiceImpl flowerOwnerServiceImpl = new FlowerOwnerServiceImpl();
        String sqlSelectFlower1 = "select id,name,typeName,owner_id,store_id,price from flower where id = 5";
        //ownerId是1 更新前是8000 花id是5 似水伊人 123
        FlowerDaoImpl flowerDao = new FlowerDaoImpl();
        List<Flower> flowers1  = flowerDao.selectFlower(sqlSelectFlower1, null);
        Flower flower = flowers1.get(0);
        //设置storeId为2 更新前为7155
        flower.setStoreId(2);
        flowerOwnerServiceImpl.sell(flower);
        //验证数据
        //验证owner
        FlowerOwnerDaoImpl flowerOwnerDaoImpl = new FlowerOwnerDaoImpl();
        String sqlSelectOwner = "select id,name,password,money from flowerowner where id = 1";
        FlowerOwner flowerSelectOwner = flowerOwnerDaoImpl.selectOwner(sqlSelectOwner, null);
        context.testcase((FlowerOwner b) -> new Double(b.getMoney()).intValue() == 8123).score(25).description("修改成功").test(flowerSelectOwner);
        //验证store
        FlowerStoreDaoImpl flowerStoreDaoImpl1 = new FlowerStoreDaoImpl();
        String sqlSelectStore = "select id,name,password,balance from flowerstore where id = 2";
        FlowerStore FlowerSelectStore = flowerStoreDaoImpl1.getFlowerStore(sqlSelectStore, null);
        context.testcase((FlowerStore b) -> new Double(b.getBalance()).intValue() == 7032).score(25).description("修改成功").test(FlowerSelectStore);
        //验证flower
        FlowerDaoImpl flowerDaoImpl = new FlowerDaoImpl();
        String sqlSelectFlower = "select id,name,typeName,owner_id,store_id,price from flower where id = 5";
        List<Flower> flowers = flowerDaoImpl.selectFlower(sqlSelectFlower, null);
        Flower selectFlower = flowers.get(0);
        context.testcase((Flower b) -> b.getStoreId() == 2).score(25).description("修改成功").test(selectFlower);
        //恢复数据
        BaseDao baseDao = new BaseDao();
        //恢复owner
        String sqlOwner = "update flowerowner set money = 8000 where id = 1";
        //恢复store
        String sqlStore = "update flowerstore set balance = 7155 where id = 2";
        //恢复flower
        String sqlFlower = "update flower set store_id = null, owner_id = 1 where id = 5";
        //删除account
        String sqlAccount = "delete from account where id is null";
        baseDao.executeSQL(sqlOwner, null);
        baseDao.executeSQL(sqlStore, null);
        baseDao.executeSQL(sqlFlower, null);
        baseDao.executeSQL(sqlAccount, null);
        context.printResult();
    }
}
