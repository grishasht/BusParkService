package model.entity;

public class Bus {
    private Integer id;
    private String name;
    private Integer numPlaces;
    private Boolean isFree;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumPlaces() {
        return numPlaces;
    }

    public void setNumPlaces(Integer numPlaces) {
        this.numPlaces = numPlaces;
    }

    public Boolean getFree() {
        return isFree;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }
}
