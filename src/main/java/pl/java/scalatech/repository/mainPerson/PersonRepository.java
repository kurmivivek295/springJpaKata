package pl.java.scalatech.repository.mainPerson;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Optional;

import pl.java.scalatech.domain.ShallowPerson;
import pl.java.scalatech.domain.mainPerson.Person;

@Transactional(readOnly = true)
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByEmail(String email);

    @Query("select u from #{#entityName} u")
    Stream<Person>  all();

    @Transactional(readOnly = true)
    @Query("select p from Person p")
    Stream<Person> streamAllPerson();

    ShallowPerson findByFirstname(String firstName);

    @Query("SELECT p from Person p where p.email like %?1")
    Person findByEmailUseLike(String name);


}