package id.ac.polman.astra.serojamatchmaker.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddEventReturnData implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}