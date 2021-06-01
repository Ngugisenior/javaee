package com.formula.racing.Formula.One.Racing.domain;

import javax.persistence.Entity;

@Entity
public class Progress extends AbstractEntity<Long>{

    private String task;
    private boolean createStatus;
    private boolean readStatus;
    private boolean deleteStatus;
    private boolean updateStatus;

    public Progress(String task, boolean createStatus, boolean readStatus,boolean updateStatus, boolean deleteStatus) {
        this.task = task;
        this.createStatus = createStatus;
        this.readStatus = readStatus;
        this.deleteStatus = deleteStatus;
        this.updateStatus = updateStatus;
    }

    public Progress(Long aLong) {
        super(aLong);
    }

    public Progress() {
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isCreateStatus() {
        return createStatus;
    }

    public void setCreateStatus(boolean createStatus) {
        this.createStatus = createStatus;
    }

    public boolean isReadStatus() {
        return readStatus;
    }

    public void setReadStatus(boolean readStatus) {
        this.readStatus = readStatus;
    }

    public boolean isDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public boolean isUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(boolean updateStatus) {
        this.updateStatus = updateStatus;
    }
}
