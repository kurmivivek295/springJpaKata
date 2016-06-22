package pl.java.scalatech;

import static com.google.common.collect.Maps.newHashMap;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.domain.keys.Travel;
import pl.java.scalatech.domain.keys.Trip;
import pl.java.scalatech.domain.mainPerson.Person;
import pl.java.scalatech.domain.mapkey.entityExample.Company;
import pl.java.scalatech.domain.mapkey.entityExample.Department;
import pl.java.scalatech.domain.mapkey.entityExample.Phone;
import pl.java.scalatech.domain.mapkey.entityExample.Responsibility;
import pl.java.scalatech.domain.mapkey.entityExample.Task;
import pl.java.scalatech.domain.mapkey.simple.Book;
import pl.java.scalatech.repository.PersonViewRepo;
import pl.java.scalatech.repository.keys.TravelRepo;
import pl.java.scalatech.repository.keys.TripRepo;
import pl.java.scalatech.repository.mainPerson.PersonRepository;
import pl.java.scalatech.repository.map.entityExample.DepartmentRepo;
import pl.java.scalatech.repository.map.entityExample.PersonDeptRepo;
import pl.java.scalatech.repository.map.simple.BookRepo;

@SpringBootApplication
@Slf4j
public class SpringJpaTestApplication implements CommandLineRunner{

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonViewRepo personViewRepo;
    @Autowired
    private TravelRepo travelRepo;
    @Autowired
    private TripRepo tripRepo;
    @Autowired
    private BookRepo bookRepo;


    public static void main(String[] args) {
        SpringApplication.run(SpringJpaTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for(int i = 0;i<100;i++){
      personRepository.save(Person.builder().email("przodownikR1"+i+"@gmail.com").firstname("slawek_"+i).disable(true).birthDay(ZonedDateTime.now()).build());
        }
      log.info("{} ",personRepository.findAll());


      log.info("person view : {}",personViewRepo.findAll());
      Travel travel = new Travel();
      travel.setName("lucca");
      travelRepo.save(travel);
      travel = new Travel();
      travel.setName("warsaw");
      travelRepo.save(travel);
      Trip trip = new Trip();
      trip.setName("italy");
      tripRepo.save(trip);
      trip = new Trip();
      trip.setName("german");
      tripRepo.save(trip);
        
      Map<String,String> index = newHashMap();
      index.put("exception", "123");
      index.put("mapping", "45");
      index.put("performance", "65");
      index.put("foreword", "1");
      
     // Set<String> reviews = Sets.newHashSet("slawek","tomek","olek","kalina","agnieszka","przodownik");
      Book book = Book.builder().title("jpa in action").indexMap(index)/*.reviews(reviews)*/.build();      
      bookRepo.save(book);
   

    }

  
}
