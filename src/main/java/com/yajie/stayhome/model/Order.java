package com.yajie.stayhome.model;

import java.util.Date;

/**
 * @author Lenovo
 */
public class Order {
    private String id;
    private Date createTime;
    private User user;
    private Date arrivalDate;
    private Date departureDate;
    private Integer adultNumber;
    private Integer childrenNumber;
    private RoomDetail roomDetail;
    private String comment;
    private Date arrivalTime;
    private Date departureTime;
    private Integer total;

    public Order() {
    }

    public Order(String id, Date createTime, User user, Date arrivalDate, Date departureDate, Integer adultNumber, Integer childrenNumber, RoomDetail roomDetail, String comment, Date arrivalTime, Date departureTime) {
        this.id = id;
        this.createTime = createTime;
        this.user = user;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.adultNumber = adultNumber;
        this.childrenNumber = childrenNumber;
        this.roomDetail = roomDetail;
        this.comment = comment;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public Order(String id, Date createTime, User user, Date arrivalDate, Date departureDate, Integer adultNumber, Integer childrenNumber, RoomDetail roomDetail, String comment, Date arrivalTime, Date departureTime, Integer total) {
        this.id = id;
        this.createTime = createTime;
        this.user = user;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.adultNumber = adultNumber;
        this.childrenNumber = childrenNumber;
        this.roomDetail = roomDetail;
        this.comment = comment;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.total = total;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Integer getAdultNumber() {
        return adultNumber;
    }

    public void setAdultNumber(Integer adultNumber) {
        this.adultNumber = adultNumber;
    }

    public Integer getChildrenNumber() {
        return childrenNumber;
    }

    public void setChildrenNumber(Integer childrenNumber) {
        this.childrenNumber = childrenNumber;
    }

    public RoomDetail getRoomDetail() {
        return roomDetail;
    }

    public void setRoomDetail(RoomDetail roomDetail) {
        this.roomDetail = roomDetail;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", user=" + user +
                ", arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                ", adultNumber=" + adultNumber +
                ", childrenNumber=" + childrenNumber +
                ", roomDetail=" + roomDetail +
                ", comment='" + comment + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                '}';
    }
}
