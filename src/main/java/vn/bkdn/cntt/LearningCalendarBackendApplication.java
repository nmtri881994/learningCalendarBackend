package vn.bkdn.cntt;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class LearningCalendarBackendApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		String enviroment = System.getenv("ENV");
		return application.sources(LearningCalendarBackendApplication.class).properties("spring.config.name:"+enviroment);
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(LearningCalendarBackendApplication.class, args);
	}
}
