package info.caprese.gnocchi.repository;

import info.caprese.gnocchi.entity.Gourmet;
import info.caprese.gnocchi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GourmetRepository extends JpaRepository<Gourmet, String> {
	List<Gourmet> findByBotIdAndUserIdAndDeleteFlagOrderByInsertDateDesc(String botId, String userId, boolean deleteFlag);

	Optional<Gourmet> findByBotIdAndUserIdAndGourmetIdAndDeleteFlag(String botId, String userId, String gourmetId, boolean deleteFlag);
}