package com.adcc.springboothibernate;

/**
 * Created by Liu.DA on 2019/9/11
 */

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adcc.springboothibernate.Player;
import com.adcc.springboothibernate.Team;
import com.adcc.springboothibernate.PlayerRepository;
import com.adcc.springboothibernate.TeamRepository;

@Service
public class SoccerServiceImpl implements SoccerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    public List<String> getAllTeamPlayers(long teamId) {
        List<String> result = new ArrayList<String>();
        List<Player> players = playerRepository.findByTeamId(teamId);

        for (Player player : players) {
            result.add(player.getName());

        }

        return result;

    }

    public void addBarcelonaPlayer(String name, String position, int number) {

        Team barcelona = teamRepository.findOne(1L);
        Player newPlayer = new Player();
        newPlayer.setName(name);
        newPlayer.setPosition(position);
        newPlayer.setNum(number);
        newPlayer.setTeam(barcelona);

        playerRepository.save(newPlayer);
    }
}