package com.yajie.stayhome.model;

import java.sql.Time;

/**
 * @author Lenovo
 */
public class RoomDetail {

    private Integer id;
    private Integer ownerId;
    private String introduce;
    private Integer price;
    private Integer capacity;
    private Integer size;
    private Time arrivalTime;
    private Time departureTime;
    private Integer type;
    private String picture;
    private Integer visitingTimes;

    public RoomDetail() {
    }

    public RoomDetail(Integer ownerId, String introduce, Integer price, Integer capacity, Integer size, Time arrivalTime, Time departureTime, Integer type, String picture) {
        this.ownerId = ownerId;
        this.introduce = introduce;
        this.price = price;
        this.capacity = capacity;
        this.size = size;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.type = type;
        this.picture = picture;
    }

    public RoomDetail(Integer id, Integer ownerId, String introduce, Integer price, Integer capacity, Integer size, Time arrivalTime, Time departureTime, Integer type, String picture) {
        this.id = id;
        this.ownerId = ownerId;
        this.introduce = introduce;
        this.price = price;
        this.capacity = capacity;
        this.size = size;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.type = type;
        this.picture = picture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getVisitingTimes() {
        return visitingTimes;
    }

    public void setVisitingTimes(Integer visitingTimes) {
        this.visitingTimes = visitingTimes;
    }

    @Override
    public String toString() {
        return "RoomDetail{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", introduce='" + introduce + '\'' +
                ", price=" + price +
                ", capacity=" + capacity +
                ", size=" + size +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", type=" + type +
                ", picture='" + picture + '\'' +
                ", visitingTimes=" + visitingTimes +
                '}';
    }
}
