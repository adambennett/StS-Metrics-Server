package DuelistMetrics.Server;

import DuelistMetrics.Server.util.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.*;

@SpringBootApplication
public class SpringApp {

	private static final Logger logger = Logger.getLogger("DuelistMetrics.Server.SpringApp");

	public static void main(String[] args) {
		SpringApplication.run(SpringApp.class, args);
		//LocalProccesor.runInitCommands();
		logger.info("SpringApp running...");
	}
}
