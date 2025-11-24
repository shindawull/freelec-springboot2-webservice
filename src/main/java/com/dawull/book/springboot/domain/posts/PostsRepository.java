package com.dawull.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*iBatis/myBatis의 DAO와 같은 DB Layer접근자다.
 * JPA에선 Repository라 부르며 인터페이스로 생성한다.
 * JpaRepository<Entity클래스, PK타입>을 상속하면
 * CRUD메소드가 자동으로 생성된다.
 * (어노테이션 추가할 필요없고)
 * 주의할점:::
 * Entity클래스와 기본Entity Repository는 함께 위치해야함.
 * 왜냐? entity클래스는 repository없이는 제대로된 역할수행 불가능
 * 함께 움직이기위해 도메인 패키지에서 함께 관리한다.
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
