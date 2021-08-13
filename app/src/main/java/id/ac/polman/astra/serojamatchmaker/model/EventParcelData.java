package id.ac.polman.astra.serojamatchmaker.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class EventParcelData implements Parcelable {
    String id="";
    String user_id="";
    String event_name="";
    Integer number_of_team=0;
    String elimination_type="";
    String status="";
    Integer is_saved=1;
    String date_created;
    String last_modified;

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(String last_modified) {
        this.last_modified = last_modified;
    }

    public EventParcelData() {
    }

    public EventParcelData(String event_name, Integer number_of_team, String elimination_type, String status, Integer is_saved) {
        this.event_name = event_name;
        this.number_of_team = number_of_team;
        this.elimination_type = elimination_type;
        this.status = status;
        this.is_saved = is_saved;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public Integer getNumber_of_team() {
        return number_of_team;
    }

    public void setNumber_of_team(Integer number_of_team) {
        this.number_of_team = number_of_team;
    }

    public String getElimination_type() {
        return elimination_type;
    }

    public void setElimination_type(String elimination_type) {
        this.elimination_type = elimination_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIs_saved() {
        return is_saved;
    }

    public void setIs_saved(Integer is_saved) {
        this.is_saved = is_saved;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
