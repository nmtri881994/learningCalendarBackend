package vn.bkdn.cntt;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class AttendanceCheckinApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		String enviroment = System.getenv("ENV");
		return application.sources(AttendanceCheckinApplication.class).properties("spring.config.name:"+enviroment);
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(AttendanceCheckinApplication.class, args);
	}
}
