package springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// JpaRepository : Posts 클래스로 Database를 접근하게 해줌
// JpaRepository<Entity 클래스, PK 타입>을 상속하여 기본적인 CRUD 메소드를 자동으로 생성
// Entity 클래스와 기본 Entity Repository는 함께 위치해야 함.
// -> 이후 큰 프로젝트에서는 엔티티와 레포지토리가 함께 움직여야 하므로 도메인 패키지에서 함께 관리해야함
public interface PostsRepository extends JpaRepository<Posts, Long> {
    // SpringDataJPA에서 제공하지 않는 쿼리는 아래와 같이 사용
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
