package DuelistMetrics.Server.models.infoModels;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class InfoKeyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long info_keyword_id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("keywords")
    private ModInfoBundle info;

    @ElementCollection
    private List<String> names;

    @Column(length = 4028)
    private String description;
    @Column(length = 4028)
    private String descriptionPlain;

    private String name;

    public InfoKeyword() {}

    public Long getInfo_keyword_id() {
        return info_keyword_id;
    }

    public void setInfo_keyword_id(Long info_keyword_id) {
        this.info_keyword_id = info_keyword_id;
    }

    public ModInfoBundle getInfo() {
        return info;
    }

    public void setInfo(ModInfoBundle info) {
        this.info = info;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionPlain() {
        return descriptionPlain;
    }

    public void setDescriptionPlain(String descriptionPlain) {
        this.descriptionPlain = descriptionPlain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InfoKeyword)) return false;
        InfoKeyword that = (InfoKeyword) o;
        return Objects.equals(getInfo(), that.getInfo()) &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInfo(), getName());
    }
}
