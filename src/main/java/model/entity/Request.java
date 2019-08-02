package model.entity;

public class Request {
    private Integer id;
    private Integer user_id;
    private Integer direction_id;
    private Integer bus_id;
    private Boolean is_accept;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getDirection_id() {
        return direction_id;
    }

    public void setDirection_id(Integer direction_id) {
        this.direction_id = direction_id;
    }

    public Integer getBus_id() {
        return bus_id;
    }

    public void setBus_id(Integer bus_id) {
        this.bus_id = bus_id;
    }

    public Boolean getIs_accept() {
        return is_accept;
    }

    public void setIs_accept(Boolean is_accept) {
        this.is_accept = is_accept;
    }
}
