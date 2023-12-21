package DuelistMetrics.Server.models;

import DuelistMetrics.Server.models.dto.ConfigDifferenceDTO;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.util.Date;

@NamedNativeQuery(name = "getListOfConfigDifferencesByRunLookup", query = """
SELECT
    c.category, c.type, c.setting, c.default_value AS defaultValue, c.player_value AS playerValue
FROM config_difference c
JOIN bundle_config_difference_xref x ON x.difference_id = c.id
WHERE x.bundle_id = :bundleId
""", resultSetMapping = "configDifferenceByRunDtoMapping")
@SqlResultSetMapping(
        name = "configDifferenceByRunDtoMapping",
        classes = @ConstructorResult(targetClass = ConfigDifferenceDTO.class,columns = {
                @ColumnResult(name = "category", type = String.class),
                @ColumnResult(name = "type", type = String.class),
                @ColumnResult(name = "setting", type = String.class),
                @ColumnResult(name = "defaultValue", type = String.class),
                @ColumnResult(name = "playerValue", type = String.class)
        })
)
@Entity(name = "config_difference")
public class ConfigDifference {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String category;
  private String type;
  private String setting;
  private String default_value;
  private String player_value;

  @Temporal(TemporalType.TIMESTAMP)
  @Generated(GenerationTime.INSERT)
  private Date created_date;

  @Generated(GenerationTime.INSERT)
  private String data_hash;

  public ConfigDifference() {}

  public ConfigDifference(Long id, String category, String type, String setting, String default_value, String player_value, Date created_date, String data_hash) {
    this.id = id;
    this.category = category;
    this.type = type;
    this.setting = setting;
    this.default_value = default_value;
    this.player_value = player_value;
    this.created_date = created_date;
    this.data_hash = data_hash;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSetting() {
    return setting;
  }

  public void setSetting(String setting) {
    this.setting = setting;
  }

  public String getDefault_value() {
    return default_value;
  }

  public void setDefault_value(String defaultValue) {
    this.default_value = defaultValue;
  }

  public String getPlayer_value() {
    return player_value;
  }

  public void setPlayer_value(String playerValue) {
    this.player_value = playerValue;
  }

  public Date getCreated_date() {
    return created_date;
  }

  public void setCreated_date(Date createdDate) {
    this.created_date = createdDate;
  }

  public String getData_hash() {
    return data_hash;
  }

  public void setData_hash(String dataHash) {
    this.data_hash = dataHash;
  }
}
