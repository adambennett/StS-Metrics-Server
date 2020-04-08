package DuelistMetrics.Server;

import DuelistMetrics.Server.models.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;

@SpringBootApplication
public class SpringApp {

	public static void main(String[] args) {
	  SpringApplication.run(SpringApp.class, args);

	  // BE CAREFUL WITH THIS
    //try { BundleProcessor.readIn(); } catch (IOException ignored) {}
	}

}
