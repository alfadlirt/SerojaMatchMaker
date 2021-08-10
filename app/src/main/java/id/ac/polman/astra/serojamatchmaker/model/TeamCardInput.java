package id.ac.polman.astra.serojamatchmaker.model;

public class TeamCardInput {
    private String team_name;
    private String instance_name;

    public TeamCardInput() {
    }

    public TeamCardInput(String team_name, String instance_name) {
        this.team_name = team_name;
        this.instance_name = instance_name;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getInstance_name() {
        return instance_name;
    }

    public void setInstance_name(String instance_name) {
        this.instance_name = instance_name;
    }
}
