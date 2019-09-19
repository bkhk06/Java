package com.adcc.springboothibernate;

/**
 * Created by Liu.DA on 2019/9/11
 */

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adcc.springboothibernate.Team;

@Repository

public interface TeamRepository extends CrudRepository<Team, Long> {

    Team findByPlayers(long playerId);

}
