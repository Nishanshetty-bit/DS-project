public class Criminal {
    private String id;
    private String name;
    private String crime;

    public Criminal(String id, String name, String crime) {
        this.id = id;
        this.name = name;
        this.crime = crime;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCrime() {
        return crime;
    }
}
