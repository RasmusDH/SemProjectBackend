package dtos.Example;

public class BeerDTO {

    private String name;
    private String tagline;
    private String description;

    public BeerDTO(String name, String tagline, String description) {
        this.name = name;
        this.tagline = tagline;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
