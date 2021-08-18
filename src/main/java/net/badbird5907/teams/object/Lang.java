package net.badbird5907.teams.object;

import lombok.Getter;
import net.badbird5907.blib.util.CC;
import net.badbird5907.blib.utils.StringUtils;
import net.badbird5907.teams.TeamsPlus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Lang {
    PREFIX("prefix","&b[&aTeams+&b]&r"),

    CREATED_TEAM("created-team","%prefix% &aSuccessfully created team &6%1"),
    TEAM_ALREADY_EXISTS("team-already-exists","%prefix% &cThat team already exists!"),
    TEAM_DOES_NOT_EXIST("team-does-not-exist","%prefix% &cThat team does not exist!"),
    MUST_BE_IN_TEAM("must-be-in-team","%prefix% &cYou must be in a team to do that!"),
    ALREADY_IN_TEAM("already-in-team","%prefix% &cYou are already in a team!"),

    TEAM_INFO_MESSAGE("team-info.message",new String[]{"%separator%","&b&l%1","&9 * &bOwner: %2","&9 * &bAllies: %3","&9 * &bEnemies: %4","&9 * &bMembers: &8[&f%5&7/&f%6&8] %9","%separator%"}),
    TEAM_INFO_ONLINE_MEMBER_ENTRY("team-info.online-member-entry","&a%1"),
    TEAM_INFO_OFFLINE_MEMBER_ENTRY("team-info.offline-member-entry","&c%1"),
    TEAM_INFO_ALLIES_TEAM_ENTRY("team-info.allies-team-entry","&b%1"),
    TEAM_INFO_ALLIES_PLAYER_ENTRY("team-info.allies-player-entry","&a%1"),
    TEAM_INFO_ENEMIED_PLAYER_ENTRY("team-info.enemied-player-entry"," &a%1 "),
    TEAM_INFO_ENEMIED_TEAM_ENTRY("team-info.enemied-team-entry"," &b%1 "),
    TEAM_INFO_ENEMIES_LIST("team-info.enemies-list","&8[&a%1&8] %2"),
    TEAM_INFO_ALLIES_LIST("team-info.allies-list","&8[&a%1&8] %2"),
    TEAM_INFO_MEMBERS_LIST("team-info.members-list","&8[&f%1&7/&f%2&8] %3"),

    INVITE("invite.player","&6%1&b has invited you to join the team &6%2&b do &a/team join %2&b to join!"),
    INVITE_TEAM_MESSAGE("invite.team","&aYour invite to &6%1&a has expired."),
    INVITE_EXPIRED("invite.expired","&aYour invite to &6%1&a has expired."),
    INVITE_ALREADY_SENT("invite.already-sent","&cYou already have sent %1 a invite!"),
    TEAM_JOINED("invite.joined","&c%1 has joined the team!"),

    TEMP_PVP_ENABLED("team.temp-pvp-enable","&6%1&a has enabled temp-pvp for &b%2&a seconds!"),

    TEAM_ENEMY_TEAM("enemy.team-enemy-team","&cYou have been enemied with the team &6%1&c!"),
    TEAM_NEUTRAL_TEAM("enemy.team-neutral-team","&aYour team is now neutral with &b%1"),

    TEAM_ENEMY_PLAYER("enemy.team-enemy-player","&cYou are now enemied with &6%1"),
    TEAM_NEUTRAL_PLAYER("enemy.team-neutral-player","&aYour team is now neutral with &b%1"),

    PLAYER_ENEMY_PLAYER("enemy.player-enemy-player","&cYou have been enemied with &6%1&c!"),
    PLAYER_NEUTRAL_PLAYER("enemy.player-neutral-player","&aYou are now neutral with &n%1"),

    PLAYER_ENEMY_TEAM("enemy.player-enemy-team","&cYou have been enemied with &6%1&c!"),
    PLAYER_NEUTRAL_TEAM("enemy.player-neutral-team","&aYou are now neutral with &b%1"),

    TEAM_PVP_DISALLOW("pvp.team-disallow","&cYou can't damage &6%1&c because you're in the same team as them!"),
    ALLY_PVP_DISALLOW("pvp.ally-disallow","&cYou can't damage &6%1&c because you're allied with them!")
    ;
    @Getter
    private String configPath,def;
    private List<String> messageList;
    private String finalMessage;
    Lang(String configPath,String def){
        this.configPath = configPath;
        this.def = def;
        finalMessage = TeamsPlus.getLangFile().getString(configPath,def);
    }
    Lang(String configPath,String[] def){
        this.configPath = configPath;
        messageList = TeamsPlus.getLangFile().getStringList(configPath);
        if (messageList.isEmpty())
            messageList = Arrays.asList(def);
        StringBuilder sb = new StringBuilder();
        for (String s : messageList) {
            sb.append(s).append("\n");
        }
        finalMessage = sb.toString();
    }

    public String toString(Object... placeholders) {
        return CC.translate(StringUtils.replacePlaceholders(finalMessage.replace("%prefix%",PREFIX.toString().replace("%separator%",CC.SEPARATOR)),placeholders));
    }

    @Override
    public String toString() {
        return toString();
    }
}
