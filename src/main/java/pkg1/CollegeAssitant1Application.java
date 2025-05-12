package pkg1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {
    "pkg1.Entity.student",  // all your student @Entity classes
    "pkg1.Entity.teacher"   // all your teacher @Entity classes
})
@EnableJpaRepositories(basePackages = {
    "pkg1.Entity.student",  // all your student *Repo interfaces
    "pkg1.Entity.teacher"   // all your teacher *Repo interfaces
})
@SpringBootApplication
public class CollegeAssitant1Application {
    public static void main(String[] args) {
        SpringApplication.run(CollegeAssitant1Application.class, args);
    }
}
