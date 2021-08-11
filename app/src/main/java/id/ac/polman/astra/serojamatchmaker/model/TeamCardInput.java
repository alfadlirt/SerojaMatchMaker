package id.ac.polman.astra.serojamatchmaker.model;

public class TeamCardInput {
    private String team_name;
    private String instance;

    public TeamCardInput() {
    }

    public TeamCardInput(String team_name, String instance) {
        this.team_name = team_name;
        this.instance = instance;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }
}
