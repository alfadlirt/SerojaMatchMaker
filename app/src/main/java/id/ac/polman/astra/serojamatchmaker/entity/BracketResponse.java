package id.ac.polman.astra.serojamatchmaker.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BracketResponse {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("event_id")
    @Expose
    private String eventId;
    @SerializedName("team_a")
    @Expose
    private String teamA;
    @SerializedName("team_b")
    @Expose
    private String teamB;
    @SerializedName("skor_a")
    @Expose
    private Integer skorA;
    @SerializedName("skor_b")
    @Expose
    private Integer skorB;
    @SerializedName("winner")
    @Expose
    private Object winner;
    @SerializedName("next_branch")
    @Expose
    private String nextBranch;
    @SerializedName("is_end")
    @Expose
    private Integer isEnd;
    @SerializedName("is_wo")
    @Expose
    private Integer isWo;
    @SerializedName("is_wo_moved")
    @Expose
    private Integer isWoMoved;
    @SerializedName("is_addition")
    @Expose
    private Integer isAddition;
    @SerializedName("stage_number")
    @Expose
    private Integer stageNumber;
    @SerializedName("index_number")
    @Expose
    private Integer indexNumber;
    @SerializedName("stage_type")
    @Expose
    private String stageType;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("last_modified")
    @Expose
    private String lastModified;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getIsWoMoved() {
        return isWoMoved;
    }

    public void setIsWoMoved(Integer isWoMoved) {
        this.isWoMoved = isWoMoved;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public Integer getSkorA() {
        return skorA;
    }

    public void setSkorA(Integer skorA) {
        this.skorA = skorA;
    }

    public Integer getSkorB() {
        return skorB;
    }

    public void setSkorB(Integer skorB) {
        this.skorB = skorB;
    }

    public Object getWinner() {
        return winner;
    }

    public void setWinner(Object winner) {
        this.winner = winner;
    }

    public String getNextBranch() {
        return nextBranch;
    }

    public void setNextBranch(String nextBranch) {
        this.nextBranch = nextBranch;
    }

    public Integer getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Integer isEnd) {
        this.isEnd = isEnd;
    }

    public Integer getIsWo() {
        return isWo;
    }

    public void setIsWo(Integer isWo) {
        this.isWo = isWo;
    }

    public Integer getIsAddition() {
        return isAddition;
    }

    public void setIsAddition(Integer isAddition) {
        this.isAddition = isAddition;
    }

    public Integer getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(Integer stageNumber) {
        this.stageNumber = stageNumber;
    }

    public Integer getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(Integer indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getStageType() {
        return stageType;
    }

    public void setStageType(String stageType) {
        this.stageType = stageType;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}