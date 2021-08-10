package id.ac.polman.astra.serojamatchmaker.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddEventReturnData {
    @SerializedName("event")
    @Expose
    private EventResponse event;
    @SerializedName("bracket")
    @Expose
    private List<BracketResponse> bracket = null;

    public EventResponse getEvent() {
        return event;
    }

    public void setEvent(EventResponse event) {
        this.event = event;
    }

    public List<BracketResponse> getBracket() {
        return bracket;
    }

    public void setBracket(List<BracketResponse> bracket) {
        this.bracket = bracket;
    }
}