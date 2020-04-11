package DuelistMetrics.Server;

import DuelistMetrics.Server.controllers.*;
import DuelistMetrics.Server.models.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.*;
import java.util.logging.*;

@SpringBootApplication
public class SpringApp {

	public static void main(String[] args) {
	  SpringApplication.run(SpringApp.class, args);
    Scanner scanner = new Scanner(System.in);
    System.out.println("\nWould you like to check runs folder for files? [Y/N]: ");
    String userInput = scanner.nextLine();
    if (userInput.toLowerCase().equals("y")) {
      System.out.println("\nWould you like to save TopBundles? [Y/N]: ");
      userInput = scanner.nextLine();
      boolean tops = userInput.toLowerCase().equals("y");
      System.out.println("\nWould you like to save RunLogs and PickInfos? [Y/N]: ");
      userInput = scanner.nextLine();
      boolean logsnInfos = userInput.toLowerCase().equals("y");
      BundleProcessor.parseFolder("C:/Users/eX_Di/git/StS-Metrics-Server/src/main/resources/runs", tops, logsnInfos);
    } else {
      Logger.getGlobal().info("Not checking runs folder.");
    }
    Logger.getGlobal().info("SpringApp running...");
	}
}
