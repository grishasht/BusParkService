package model.entity;

public class Request {
    private Integer id;
    private Integer userId;
    private Integer busId;
    private Integer directionId;
    private String name;
    private String number;
    private Integer numPlaces;
    private String start;
    private String end;
    private Integer distance;
    private Boolean isAccept;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public Integer getDirectionId() {
        return directionId;
    }

    public void setDirectionId(Integer directionId) {
        this.directionId = directionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getNumPlaces() {
        return numPlaces;
    }

    public void setNumPlaces(Integer numPlaces) {
        this.numPlaces = numPlaces;
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

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Boolean getAccept() {
        return isAccept;
    }

    public void setAccept(Boolean accept) {
        isAccept = accept;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsAccept() {
        return isAccept;
    }

    public void setIsAccept(Boolean isAccept) {
        this.isAccept = isAccept;
    }
}
