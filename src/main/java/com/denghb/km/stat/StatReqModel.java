package com.denghb.km.stat;

/**
 * 统计规则
 *
 * @author denghb
 * @since 2021/09/13 15:47
 */
public class StatReqModel {

    /**
     * yyyy-MM-dd
     * 日期必填
     */
    private String day;

    /**
     * 开始时间
     * HH:mm
     */
    private String timeStart;

    /**
     * 截止时间
     * HH:mm
     */
    private String timeEnd;

    /**
     * 最近xx秒
     */
    private Integer lastTime;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Integer getLastTime() {
        return lastTime;
    }

    public void setLastTime(Integer lastTime) {
        this.lastTime = lastTime;
    }

    @Override
    public String toString() {
        return "StatReqModel{" +
                "day='" + day + '\'' +
                ", timeStart='" + timeStart + '\'' +
                ", timeEnd='" + timeEnd + '\'' +
                ", lastTime=" + lastTime +
                '}';
    }
}
