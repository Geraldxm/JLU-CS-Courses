package com.huawei.classroom.teacher;

import java.util.List;

import com.huawei.classroom.context.ClassContext;
import com.huawei.classroom.student.dao.BaseDao;
import com.huawei.classroom.student.dao.impl.FlowerDaoImpl;
import com.huawei.classroom.student.dao.impl.FlowerStoreDaoImpl;
import com.huawei.classroom.student.entity.Account;
import com.huawei.classroom.student.entity.Flower;
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
        FlowerDaoImpl flowerDaoImpl = new FlowerDaoImpl();
        List<Flower> result1 = flowerDaoImpl.getAllFlower();
        context.testcase((List<Account> b) -> b.size()==11).score(25).description("有11中花").test(result1);
        FlowerStoreDaoImpl flowerStoreDaoImpl = new FlowerStoreDaoImpl();
        List<FlowerStore> result2 = flowerStoreDaoImpl.getAllStore();
        context.testcase((List<FlowerStore> b) -> b.size()==3).score(25).description("有3个商店").test(result2);
        String sql = "select * from flowerstore where name = ? ";
        String[] param = {"小丑鲜花店"};
        FlowerStore flowerStore = flowerStoreDaoImpl.getFlowerStore(sql, param);
        context.testcase((FlowerStore b) -> b != null).score(25).description("存在小丑鲜花店").test(flowerStore);
//        BaseDao baseDao = new BaseDao();
//        String sql2 = "insert  into `flowerstore`(`name`,`password`,`balance`) values (?,?,?)";
//        String[] param2 = {"小丑鲜花店1","123","256"};
//        baseDao.executeSQL(sql2, param2);
        String sql1 = "update flowerstore set password = ? where name = ?";
        String[] param1 = {"小丑鲜花店","小丑鲜花店"};
        int count = flowerStoreDaoImpl.updateStore(sql1,param1);
        context.testcase((Integer b) -> b.intValue() == 1).score(25).description("更新成功").test(new Integer(count));
        context.printResult();
    }
}
