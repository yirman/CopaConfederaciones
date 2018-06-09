package com.example.german.copaconfederaciones.models.get;

/**
 * Created by German on 3/6/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Item extends RealmObject {

    @SerializedName("matchDay")
    @Expose
    private MatchDay matchDay;
    @SerializedName("eventStatus")
    @Expose
    private EventStatus eventStatus;
    @SerializedName("statusCategory")
    @Expose
    private String statusCategory;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("homeTeam")
    @Expose
    private HomeTeam homeTeam;
    @SerializedName("awayTeam")
    @Expose
    private AwayTeam awayTeam;
    @SerializedName("homeScore")
    @Expose
    private int homeScore;
    @SerializedName("awayScore")
    @Expose
    private int awayScore;
    @SerializedName("matchTime")
    @Expose
    private int matchTime;
    @SerializedName("endDate")
    @Expose
    private String endDate;

    public MatchDay getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(MatchDay matchDay) {
        this.matchDay = matchDay;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public String getStatusCategory() {
        return statusCategory;
    }

    public void setStatusCategory(String statusCategory) {
        this.statusCategory = statusCategory;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HomeTeam getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(HomeTeam homeTeam) {
        this.homeTeam = homeTeam;
    }

    public AwayTeam getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(AwayTeam awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public int getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(int matchTime) {
        this.matchTime = matchTime;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
