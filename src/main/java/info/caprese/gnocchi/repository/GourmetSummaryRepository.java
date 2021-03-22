package info.caprese.gnocchi.repository;

import info.caprese.gnocchi.entity.Gourmet;
import info.caprese.gnocchi.entity.GourmetSumarry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GourmetSummaryRepository extends JpaRepository<GourmetSumarry, String> {
	@Query(value = "SELECT gourmet.gourmet_id, gourmet.menu, gourmet.shop_name, gourmet.comment, gourmet.tweet_count, gourmet.update_date, gourmet.insert_date, users.display_name FROM gourmet inner join users where gourmet.user_id = users.user_id and gourmet.delete_flag='0' and gourmet.invalid_flag='0'", nativeQuery = true)
	List<GourmetSumarry> find();
}