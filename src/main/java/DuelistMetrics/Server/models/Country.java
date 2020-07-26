package DuelistMetrics.Server.models;

import java.util.*;

public class Country implements Comparable<Country> {

    public String name;
    public String id;

    public Country(String name, String id) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;
        Country country = (Country) o;
        return Objects.equals(name, country.name) &&
                Objects.equals(id, country.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public int compareTo(Country o) {
        return this.name.compareTo(o.name);
    }
}
