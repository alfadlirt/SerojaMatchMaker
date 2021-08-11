package id.ac.polman.astra.serojamatchmaker.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventInput {
    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("event_name")
    @Expose
    private String event_name;

    @SerializedName("number_of_team")
    @Expose
    private Integer number_of_team;

    @SerializedName("elimination_type")
    @Expose
    private String elimination_type;

    @SerializedName("team_list_json")
    @Expose
    private String team_list_json;

    public EventInput(String user_id, String event_name, Integer number_of_team, String elimination_type, String team_list_json) {
        this.user_id = user_id;
        this.event_name = event_name;
        this.number_of_team = number_of_team;
        this.elimination_type = elimination_type;
        this.team_list_json = team_list_json;
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

    public String getTeam_list_json() {
        return team_list_json;
    }

    public void setTeam_list_json(String team_list_json) {
        this.team_list_json = team_list_json;
    }
}
