package id.ac.polman.astra.serojamatchmaker.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Event implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("event_name")
    @Expose
    private String eventName;
    @SerializedName("number_of_team")
    @Expose
    private Integer numberOfTeam;
    @SerializedName("elimination_type")
    @Expose
    private String eliminationType;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("is_saved")
    @Expose
    private Integer isSaved;
    @SerializedName("last_modified")
    @Expose
    private String lastModified;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Integer getNumberOfTeam() {
        return numberOfTeam;
    }

    public void setNumberOfTeam(Integer numberOfTeam) {
        this.numberOfTeam = numberOfTeam;
    }

    public String getEliminationType() {
        return eliminationType;
    }

    public void setEliminationType(String eliminationType) {
        this.eliminationType = eliminationType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIsSaved() {
        return isSaved;
    }

    public void setIsSaved(Integer isSaved) {
        this.isSaved = isSaved;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}