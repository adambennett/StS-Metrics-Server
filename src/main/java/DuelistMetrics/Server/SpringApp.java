package DuelistMetrics.Server;

import DuelistMetrics.Server.util.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.*;

@SpringBootApplication
public class SpringApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringApp.class, args);
		//LocalProccesor.runInitCommands();
		Logger.getGlobal().info("SpringApp running...");
	}
}
