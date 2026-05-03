package kicc.cicd.repository;

import kicc.cicd.domain.Diary;
import kicc.cicd.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query("select i.url from Image i where i.diary=:diary")
    List<String> findAllUrlByDiary(@Param("diary") Diary diary);
}
