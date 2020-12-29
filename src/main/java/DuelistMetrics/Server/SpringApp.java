package DuelistMetrics.Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.*;
import java.util.logging.*;

@SpringBootApplication
@EnableScheduling
public class SpringApp {

	private static final Logger logger = Logger.getLogger("DuelistMetrics.Server.SpringApp");

	public static void main(String[] args) {
		SpringApplication.run(SpringApp.class, args);
		logger.info("DuelistMod Metrics Server started and ready");
		//LocalCommands.updateDuelistEvents();
		//LocalProccesor.runInitCommands();
		//logger.info("SpringApp finished with local commands. Running...");
	}
}
