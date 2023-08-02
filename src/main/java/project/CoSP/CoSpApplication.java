package project.CoSP;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import project.CoSP.Model.Code;
import java.time.LocalDateTime;

@SpringBootApplication
@RestController
public class CoSpApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoSpApplication.class, args);
	}

	@Component
	public class Runner implements CommandLineRunner{
		private final codeService service;

		public Runner(codeService service){
			this.service = service;
		}

		@Override
		public void run(String... args){
            service.firstSave(new Code("body ...1", LocalDateTime.now(), 5000, 100));
            service.firstSave(new Code("body ...2", LocalDateTime.now(), 5000, 100));
            service.firstSave(new Code("body ...3", LocalDateTime.now(), 5000, 100));
            service.firstSave(new Code("body ...4", LocalDateTime.now(), 5000, 100));
            service.firstSave(new Code("body ...5", LocalDateTime.now(), 5000, 100));
            service.firstSave(new Code("body ...6", LocalDateTime.now(), 5000, 100));
            service.firstSave(new Code("body ...7", LocalDateTime.now(), 5000, 100));
            service.firstSave(new Code("body ...8", LocalDateTime.now(), 5000, 100));
            service.firstSave(new Code("body ...9", LocalDateTime.now(), 5000, 100));
            service.firstSave(new Code("body ...10", LocalDateTime.now(), 5000, 100));
            service.firstSave(new Code("body ...1A", LocalDateTime.now(), 0, 0));
            service.firstSave(new Code("body ...2A", LocalDateTime.now(), 0, 0));
            service.firstSave(new Code("body ...3A", LocalDateTime.now(), 0, 0));
            service.firstSave(new Code("body ...4A", LocalDateTime.now(), 0, 0));
            service.firstSave(new Code("body ...5A", LocalDateTime.now(), 0, 0));
            service.firstSave(new Code("body ...6A", LocalDateTime.now(), 0, 0));
            service.firstSave(new Code("body ...7A", LocalDateTime.now(), 0, 0));
            service.firstSave(new Code("body ...8A", LocalDateTime.now(), 0, 0));
            service.firstSave(new Code("body ...11", LocalDateTime.now(), 5000, 100));
            service.firstSave(new Code("body ...12", LocalDateTime.now(), 5000, 100));
            service.firstSave(new Code("body ...13", LocalDateTime.now(), 5000, 100));
		}

	}

}
