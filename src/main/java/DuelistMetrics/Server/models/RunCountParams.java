package DuelistMetrics.Server.models;

public class RunCountParams {

    public RunCountParamType types;
    public Boolean noTypes;
    public Integer pageNumber;
    public Integer pageSize;

    public record RunCountParamType(String character, String country, String timeStart, String timeEnd, boolean duelist,
                                    boolean nonDuelist, String host, Integer ascensionStart, Integer ascensionEnd,
                                    Integer challengeStart, Integer challengeEnd, Boolean victory, Integer floorStart,
                                    Integer floorEnd, String deck, String killedBy){}

    public RunCountParams() {}

    public RunCountParams(RunCountParamType types, Boolean noTypes, int pageSize, int pageNum) {
        this.pageNumber = pageNum;
        this.pageSize = pageSize;
        this.types = types;
        this.noTypes = noTypes;
    }

    public RunCountParamType getTypes() {
        return types;
    }

    public void setTypes(RunCountParamType types) {
        this.types = types;
    }

    public Boolean getNoTypes() {
        return noTypes;
    }

    public void setNoTypes(Boolean noTypes) {
        this.noTypes = noTypes;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "RunCountParams{" +
                "types=" + types +
                ", noTypes=" + noTypes +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }
}
