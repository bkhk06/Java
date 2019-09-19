package com.adcc.springboothibernate;

/**
 * Created by Liu.DA on 2019/9/11
 */

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adcc.springboothibernate.Player;

@Repository

public interface PlayerRepository extends CrudRepository<Player, Long> {

    List<Player> findByTeamId(long teamId);

}
