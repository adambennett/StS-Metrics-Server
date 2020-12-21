package DuelistMetrics.Server.util;

import DuelistMetrics.Server.models.*;

import static com.google.common.base.Preconditions.*;
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
      BundleProcessor.gpi(gpi);
      Logger.getGlobal().info("Not checking runs folder.");
    }
  }

  public static String getDayOfMonthSuffix(final int n) {
    checkArgument(n >= 1 && n <= 31, "illegal day of month: " + n);
    if (n >= 11 && n <= 13) {
      return "th";
    }
    switch (n % 10) {
      case 1:  return "st";
      case 2:  return "nd";
      case 3:  return "rd";
      default: return "th";
    }
  }

  public static float calculatePercentile(int position, int sizeOfData) {
    return calculatePercentile(position, sizeOfData, true);
  }

  public static float calculatePercentile(int position, int sizeOfData, boolean aboveForty) {
     float y = aboveForty ? 1.00f : 0.40f;
     float x = y * sizeOfData;
     while (position < x) {
      y -= 0.01;
      if (y < 0.01) {
        return 0.0f;
      }
      x = y * sizeOfData;
     }
     return y;
  }

}
