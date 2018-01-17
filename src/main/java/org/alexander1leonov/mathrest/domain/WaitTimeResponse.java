package org.alexander1leonov.mathrest.domain;

public class WaitTimeResponse {
    private boolean success;
    private WaitTimeCurrent current;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public WaitTimeCurrent getCurrent() {
        return current;
    }

    public void setCurrent(WaitTimeCurrent current) {
        this.current = current;
    }
}
