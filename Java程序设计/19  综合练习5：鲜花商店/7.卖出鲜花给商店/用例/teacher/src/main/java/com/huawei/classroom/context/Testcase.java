package com.huawei.classroom.context;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * 测试用例，定义用例判定逻辑并执行用例。
 * @author kenly
 */
public class Testcase {
    private Predicate predicate;
    private Integer score;
    private String description;
    private TestResult testResult = new TestResult();

    public Integer getScore() {
        return score;
    }

    /**
     * 用例分值。
     * @param score
     * @return
     */
    public Testcase score(Integer score) {
        if (score < 0) {
            score = 0;
        }
        this.score = score;
        testResult.setScore(score);
        return this;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 用例描述。
     * @param description
     * @return
     */
    public Testcase description(String description) {
        this.description = description;
        testResult.setDescription(description);
        return this;
    }

    /**
     * 用例判定逻辑。
     * @param predicate
     * @return
     */
    public Testcase predicate(Predicate predicate) {
        Objects.requireNonNull(predicate);
        this.predicate = predicate;
        return this;
    }

    /**
     * 执行用例，并记录每次执行记录。
     * @param testObject
     * @return
     */
    public Testcase test(Object testObject) {
        Boolean r = this.predicate.test(testObject);
        TestRecord record = new TestRecord();
        record.setTestData(testObject);
        record.setPassed(r);
        record.setActiveScore(r ? this.getScore() : 0);
        record.setCommitTime(System.currentTimeMillis());

        this.testResult.getRecords().add(record);
        this.testResult.stat();

        return this;
    }

    /**
     *  获取测试结果。
     * @return
     */
    public TestResult getResult() {
        return this.testResult;
    }

}
