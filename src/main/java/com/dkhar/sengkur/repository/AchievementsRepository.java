package com.dkhar.sengkur.repository;

import com.dkhar.sengkur.model.T_AllAchievements;
import com.dkhar.sengkur.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AchievementsRepository extends JpaRepository<T_AllAchievements, Long> {

 T_AllAchievements save(T_AllAchievements t);
 @Query(value = "SELECT t FROM T_AllAchievements t where achievement_id.achievement_id=?1 order by achievement_id.achievement_name")
 List<T_AllAchievements> findByAchievement_Id(long id);

}
