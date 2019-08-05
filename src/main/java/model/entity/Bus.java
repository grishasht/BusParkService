package model.entity;

public class Bus {
    private Integer id;
    private String name;
    private Integer number;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
