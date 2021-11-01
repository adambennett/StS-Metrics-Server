package DuelistMetrics.Server.models;

public class RunCountParams {

    public String type;
    public String secondType;
    public String hofType;

    public RunCountParams() {}

    public RunCountParams(String type, String secondType, String hofType) {
        this.type = type;
        this.secondType = secondType;
        this.hofType = hofType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSecondType() {
        return secondType;
    }

    public void setSecondType(String secondType) {
        this.secondType = secondType;
    }

    public String getHofType() {
        return hofType;
    }

    public void setHofType(String hofType) {
        this.hofType = hofType;
    }
}
