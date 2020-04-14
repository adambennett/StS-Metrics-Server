package DuelistMetrics.Server.util;

import DuelistMetrics.Server.models.*;

import java.util.*;
import java.util.logging.*;

public class LocalProccesor {

  public static void runInitCommands() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("\nWould you like to generate PickInfos? [Y/N]: ");
    String userInput = scanner.nextLine();
    boolean gpi = userInput.toLowerCase().equals("y");
    System.out.println("\nWould you like to check runs folder for files? [Y/N]: ");
    userInput = scanner.nextLine();
    if (userInput.toLowerCase().equals("y")) {
      System.out.println("\nWould you like to save TopBundles? [Y/N]: ");
      userInput = scanner.nextLine();
      boolean tops = userInput.toLowerCase().equals("y");
      System.out.println("\nWould you like to save RunLogs and PickInfos? [Y/N]: ");
      userInput = scanner.nextLine();
      boolean logsnInfos = userInput.toLowerCase().equals("y");
      BundleProcessor.parseFolder("C:/Users/eX_Di/git/StS-Metrics-Server/src/main/resources/runs", tops, logsnInfos, gpi);
    } else {
      Logger.getGlobal().info("Not checking runs folder.");
    }
    Logger.getGlobal().info("SpringApp running...");
  }

}