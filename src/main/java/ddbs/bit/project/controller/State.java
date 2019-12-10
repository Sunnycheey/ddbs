package ddbs.bit.project.controller;

public class State {
    private int stateCode;
    private String message;

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "State{" +
                "stateCode=" + stateCode +
                ", message='" + message + '\'' +
                '}';
    }
}
