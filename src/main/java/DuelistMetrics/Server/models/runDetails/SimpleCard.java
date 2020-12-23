package DuelistMetrics.Server.models.runDetails;

public class SimpleCard {
    public String name;
    public String id;

    public SimpleCard() {}

    public SimpleCard(String id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
