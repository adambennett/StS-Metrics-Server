package DuelistMetrics.Server.models;

public class RunLogCriteria {

    public RunLogFilter filter;
    public String pageNumber;
    public String pageSize;

    public RunLogCriteria() {}

    public RunLogCriteria(RunLogFilter filter, String pageNumber, String pageSize) {
        this.filter = filter;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public RunLogFilter getFilter() {
        return filter;
    }

    public void setFilter(RunLogFilter filter) {
        this.filter = filter;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
