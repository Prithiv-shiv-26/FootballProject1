package com.footballleague.footballleague.repository;

import com.footballleague.footballleague.entity.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FixtureRepository extends JpaRepository<Fixture, Long> {
}
