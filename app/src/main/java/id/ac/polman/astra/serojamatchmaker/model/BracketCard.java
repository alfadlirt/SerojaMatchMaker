package id.ac.polman.astra.serojamatchmaker.model;

public class BracketCard {
    private String id_bracket;
    private String team_a_name="waiting team...";
    private Integer skor_a_name=0;
    private String team_b_name="waiting team...";
    private Integer skor_b_name=0;
    private boolean is_wo;
    private boolean is_wo_moved;
    private String stage_type;

    public boolean isIs_wo_moved() {
        return is_wo_moved;
    }

    public String getStage_type() {
        return stage_type;
    }

    public void setStage_type(String stage_type) {
        this.stage_type = stage_type;
    }

    public boolean getIs_wo_moved() {
        return is_wo_moved;
    }

    public void setIs_wo_moved(boolean is_wo_moved) {
        this.is_wo_moved = is_wo_moved;
    }

    private String status;

    public boolean isIs_wo() {
        return is_wo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BracketCard() {
    }

    public String getId_bracket() {
        return id_bracket;
    }

    public void setId_bracket(String id_bracket) {
        this.id_bracket = id_bracket;
    }

    public boolean getIs_wo() {
        return is_wo;
    }

    public void setIs_wo(boolean is_wo) {
        this.is_wo = is_wo;
    }

    public BracketCard(String team_a_name, Integer skor_a_name, String team_b_name, Integer skor_b_name) {
        this.team_a_name = team_a_name;
        this.skor_a_name = skor_a_name;
        this.team_b_name = team_b_name;
        this.skor_b_name = skor_b_name;
    }

    public String getTeam_a_name() {
        return team_a_name;
    }

    public void setTeam_a_name(String team_a_name) {
        this.team_a_name = team_a_name;
    }

    public Integer getSkor_a_name() {
        return skor_a_name;
    }

    public void setSkor_a_name(Integer skor_a_name) {
        this.skor_a_name = skor_a_name;
    }

    public String getTeam_b_name() {
        return team_b_name;
    }

    public void setTeam_b_name(String team_b_name) {
        this.team_b_name = team_b_name;
    }

    public Integer getSkor_b_name() {
        return skor_b_name;
    }

    public void setSkor_b_name(Integer skor_b_name) {
        this.skor_b_name = skor_b_name;
    }
}
