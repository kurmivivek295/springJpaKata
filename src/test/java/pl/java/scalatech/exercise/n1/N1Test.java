package pl.java.scalatech.exercise.n1;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.websocket.Session;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.collect.Maps.newHashMap;
import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.JpaLoggerConfig;
import pl.java.scalatech.config.PropertiesLoader;
import pl.java.scalatech.config.hikari.HikariCPConfiguration;
import pl.java.scalatech.domain.example.n1.Skill;
import pl.java.scalatech.repository.n1.CandidateRepo;
import pl.java.scalatech.repository.n1.SkillRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PropertiesLoader.class, JpaJN1Config.class,JpaLoggerConfig.class ,HikariCPConfiguration.class })
@ActiveProfiles(value = {"n1","logger","dev"})
@Transactional
@SqlDataN1
@Slf4j
public class N1Test {

    @Autowired
    private EntityManager em;
    {
        
       
    }

    @Autowired
    private SkillRepo skillRepo;

    @Autowired
    private CandidateRepo candidateRepo;

    @Test
    public void shouldBoostrap() {

    }
//tag::main[]
    @Test //per session 
    public void shouldRetrieveSkills() {
        
        skillRepo.findAll().forEach(s -> log.info("{}", s));
        
        
    }

    @Test
    public void shouldRetrieveSkillsUseFetchJoin() {
        skillRepo.findAllFetchJoin().forEach(s -> log.info(" fetch join skill : {}", s));
    }

    @Test
    // @Repeat(10)
    public void shouldRetrieveSkillNormal() {
        List<Skill> s = em.createQuery("FROM Skill", Skill.class).getResultList();
        log.info("skills {}", s);

    }

    @Test

    public void shouldEntityGraphWork() {

        Map<String, Object> props = newHashMap();
        EntityGraph<Skill> skillGraph = em.createEntityGraph(Skill.class);
        skillGraph.addAttributeNodes("candidate");
        props.put("javax.persistence.loadgraph", skillGraph);

        Skill one = em.find(Skill.class, 1l, props);
        assertThat(Persistence.getPersistenceUtil().isLoaded(one)).isTrue();
        assertThat(Persistence.getPersistenceUtil().isLoaded(one.getCandidate())).isTrue();

    }

    @Test
    public void shouldRetrieveWhatIWant() {
        List<Object[]> result = em.createQuery("SELECT s.name, c.fullName FROM Skill s  JOIN  s.candidate c where c.fullName = :name")
                .setParameter("name", "przodownik").getResultList();
        for (Object[] o : result) {
            log.info("skill  name : {} , candidateName : {}", o[0], o[1]);
        }
        assertThat(result).hasSize(4);
    }
// end::main[]
}
