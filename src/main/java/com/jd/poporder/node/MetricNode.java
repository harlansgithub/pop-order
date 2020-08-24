package com.jd.poporder.node;

public class MetricNode {
    private String resource;
    private int classification;
    private long timestamp;
    private long passQps;
    private long blockQps;
    private long successQps;
    private long exceptionQps;
    private long rt;
    private long occupiedPassQps;
    private long concurrency;

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public int getClassification() {
        return classification;
    }

    public void setClassification(int classification) {
        this.classification = classification;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getPassQps() {
        return passQps;
    }

    public void setPassQps(long passQps) {
        this.passQps = passQps;
    }

    public long getBlockQps() {
        return blockQps;
    }

    public void setBlockQps(long blockQps) {
        this.blockQps = blockQps;
    }

    public long getSuccessQps() {
        return successQps;
    }

    public void setSuccessQps(long successQps) {
        this.successQps = successQps;
    }

    public long getExceptionQps() {
        return exceptionQps;
    }

    public void setExceptionQps(long exceptionQps) {
        this.exceptionQps = exceptionQps;
    }

    public long getRt() {
        return rt;
    }

    public void setRt(long rt) {
        this.rt = rt;
    }

    public long getOccupiedPassQps() {
        return occupiedPassQps;
    }

    public void setOccupiedPassQps(long occupiedPassQps) {
        this.occupiedPassQps = occupiedPassQps;
    }

    public long getConcurrency() {
        return concurrency;
    }

    public void setConcurrency(long concurrency) {
        this.concurrency = concurrency;
    }
}
