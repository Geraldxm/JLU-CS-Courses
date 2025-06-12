package com.huawei.classroom.context;

import com.google.gson.Gson;

/**
 * 测试记录，记录测试对象、测试得分和测试时间等。
 */
public class TestRecord {

    private Object testData; //测试对象
    private Boolean passed; //是否通过
    private Integer activeScore; //测试得分
    private long commitTime; //测试时间


    public Integer getActiveScore() {
        return activeScore;
    }

    public void setActiveScore(Integer activeScore) {
        this.activeScore = activeScore;
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }

    public long getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(long commitTime) {
        this.commitTime = commitTime;
    }

    public Object getTestData() {
        return testData;
    }

    public void setTestData(Object testData) {
        this.testData = testData;
    }

    /**
     * 测试结果转换成JSON格式。
     * @return toJson
     */
    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
