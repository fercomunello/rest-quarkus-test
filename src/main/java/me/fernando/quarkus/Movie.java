package me.fernando.quarkus;

public class Movie {

    private Long id;
    private String name;

    public Movie(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Movie() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
