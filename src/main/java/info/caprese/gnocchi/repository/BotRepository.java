package info.caprese.gnocchi.repository;

import info.caprese.gnocchi.entity.Bot;
import info.caprese.gnocchi.entity.Gourmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BotRepository extends JpaRepository<Bot, String> {
}