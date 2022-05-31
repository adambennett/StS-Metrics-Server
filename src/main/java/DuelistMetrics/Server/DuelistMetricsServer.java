package DuelistMetrics.Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.*;
import java.util.logging.*;

@SpringBootApplication
//@EnableScheduling
public class DuelistMetricsServer {

	private static final Logger logger = Logger.getLogger("DuelistMetricsServer");

	public static void main(String[] args) {
		SpringApplication.run(DuelistMetricsServer.class, args);
		//LocalCommands.updateDuelistEvents();
		//LocalProccesor.runInitCommands();
		//logger.info("SpringApp finished with local commands. Running...");
	}
}
