package com.tinnovat.app.midland;

public class WorkStatusReportListView {
    private String project;
    private String work;
    private String workName;
    private String actualRate;
    private String estimatedRate;

    public WorkStatusReportListView(String project, String work, String workName, String actualRate , String estimatedRate) {
        this.project = project;
        this.work = work;
        this.workName = workName;
        this.actualRate = actualRate;
        this.estimatedRate = estimatedRate;
    }

    public WorkStatusReportListView() {
    }

    public String getName() {
        return project;
    }

    public void setName(String name) {
        this.project = name;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getActalRate() {
        return actualRate;
    }

    public void setActalRate(String actalRate) {
        this.actualRate = actalRate;
    }

    public String getEstimatedRate() {
        return estimatedRate;
    }

    public void setEstimatedRate(String estimatedRate) {
        this.estimatedRate = estimatedRate;
    }



}