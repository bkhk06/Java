package com.adcc.springboothibernate;

/**
 * Created by Liu.DA on 2019/9/11
 */
import java.util.List;

public interface SoccerService {

    public List<String> getAllTeamPlayers(long teamId);

    public void addBarcelonaPlayer(String name, String position, int number);

}
