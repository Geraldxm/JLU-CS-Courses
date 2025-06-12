package com.huawei.classroom.context;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 测试结果，含每次测试记录。
 */
public class TestResult {

    private String testcaseId; //用例ID
    private String description; //用例描述
    private Integer score; //用例分值
    private List<TestRecord> records=new ArrayList(); //测试记录
    private long testCount; //测试总次数
    private long passedCount; //测试通过数
    private long failedCount; //测试失败数

    public long getTestCount() {
        return this.testCount;
    }

    public void setTestCount(long testCount) {
        this.testCount = testCount;
    }

    public long getPassedCount() {
        return this.passedCount;
    }

    public void setPassedCount(long passedCount) {
        this.passedCount = passedCount;
    }

    public long getFailedCount() {
        return this.failedCount;
    }

    public void setFailedCount(long failedCount) {
        this.failedCount = failedCount;
    }

    public List<TestRecord> getRecords() {
        return records;
    }

    public void setRecords(List<TestRecord> records) {
        Objects.requireNonNull(records);
        this.records = records;
    }

    public String getTestcaseId() {
        return testcaseId;
    }

    public void setTestcaseId(String testcaseId) {
        this.testcaseId = testcaseId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * 测试结果，含每次测试记录。
     */
    public TestResult(){

    }

    /**
     * 测试结果，含每次测试记录。
     * @param records
     */
    public TestResult(List<TestRecord> records) {
        Objects.requireNonNull(records);
        this.setRecords(records);
    }

    /**
     * 统计测试总数、测试通过数和测试失败数。
     */
    public void stat(){
        this.setTestCount(this.records.stream().count());
        this.setPassedCount(this.records.stream().filter(r -> r.getPassed()).count());
        this.setFailedCount(this.records.stream().filter(r -> !r.getPassed()).count());
    }

    /**
     * 测试结果转换成JSON格式。
     * @return
     */
    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
