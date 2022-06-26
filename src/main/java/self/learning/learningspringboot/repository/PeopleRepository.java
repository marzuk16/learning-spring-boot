package self.learning.learningspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import self.learning.learningspringboot.entity.People;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {
}
