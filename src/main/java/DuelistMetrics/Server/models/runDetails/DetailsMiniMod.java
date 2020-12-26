package DuelistMetrics.Server.models.runDetails;

import DuelistMetrics.Server.models.infoModels.*;

import java.util.*;

public class DetailsMiniMod {

    private String modID;
    private String modVersion;
    private String name;
    private List<String> authors;

    public DetailsMiniMod(MiniMod mod) {
        this.modID = mod.getModID();
        this.modVersion = mod.getModVersion();
        this.name = mod.getName();
        this.authors = mod.getAuthors();
    }

    public String getModID() {
        return modID;
    }

    public void setModID(String modID) {
        this.modID = modID;
    }

    public String getModVersion() {
        return modVersion;
    }

    public void setModVersion(String modVersion) {
        this.modVersion = modVersion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
}
