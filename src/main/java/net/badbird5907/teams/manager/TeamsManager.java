package net.badbird5907.teams.manager;

import lombok.Getter;
import net.badbird5907.teams.TeamsPlus;
import net.badbird5907.teams.object.Team;
import net.badbird5907.teams.storage.StorageHandler;
import org.apache.commons.lang.StringEscapeUtils;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TeamsManager {
    public static TeamsManager getInstance(){
        return TeamsPlus.getInstance().getTeamsManager();
    }
    public TeamsManager(){
        loadTeams(StorageManager.getStorageHandler());
    }
    public void loadTeams(StorageHandler storageHandler){
        this.teams = storageHandler.getTeams();
    }
    public void saveTeams(StorageHandler storageHandler){
        for (Team team : teams) {
            storageHandler.saveTeam(team);
        }
    }
    @Getter
    private Set<Team> teams = new HashSet<>(); //keep teams loaded in memory so we don't need to keep loading from data source

    @Nullable
    public Team getTeamByName(String name){
        String a = StringEscapeUtils.escapeJava(name);
        if (StorageManager.getStorageHandler().getClass().getName().toLowerCase().contains("sql")) //prevent sql injection attacks
            a = StringEscapeUtils.escapeSql(name);
        return teams.stream().filter(team -> team.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
    @Nullable
    public Team getTeamById(UUID id){
        return teams.stream().filter(team -> team.getTeamId().toString().equalsIgnoreCase(id.toString())).findFirst().orElse(null); //i like to compare the uuid using tostring because sometimes UUID == UUID returns false :shrug:
    }
    @Nullable
    public Team getPlayerTeam(UUID player){
        return teams.stream().filter(team -> team.getMembers().keySet().contains(player)).findFirst().orElse(null);
    }
}
