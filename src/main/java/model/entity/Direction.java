package model.entity;

public class Direction {
    private Integer id;
    private Integer distance;
    private String start;
    private String end;
    private Boolean isFree;
    private Integer busId;

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public Boolean getFree() {
        return isFree;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Boolean getIsFree() {
        return isFree;
    }

    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }
}
