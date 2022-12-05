package se.lexicon;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ExceptionDemo {

  public static void main(String[] args) {
    /*do{
      try{
       String studentId = ex4();
        System.out.println(studentId);
        break;
      }catch (IllegalArgumentException e){
        System.out.println(e.getMessage());
      }
    }while (true);*/

    /*try{
      ex6();
    }catch (DataNotFoundException e){
      System.out.println("Error Code: " +e.getErrorCode()  + " ErrorMessage: " + e.getParamValue() + " " + e.getMessage());
    }*/

    /*try {
      ex7();
    } catch (InsufficientFoundsException e) {
      System.out.println(e.getMessage());
    }*/

    //ex8();


    System.out.println(ConsoleColors.BLUE);
    System.out.println("---------------" );
    System.out.println("display menu");
    System.out.println("display menu");
    System.out.println("display menu");
    System.out.println(ConsoleColors.RESET);
    System.out.println(ConsoleColors.RED + "test");


  }


  // Checked Exception
  public static void ex1() {
    while (true) {
      try {
        // Protected Code
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a valid file path: ");
        String filePath = scanner.nextLine();


        // https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html
        BufferedReader reader = Files.newBufferedReader(Paths.get(filePath));
        List<String> skillList = reader.lines().collect(Collectors.toList());
        skillList.forEach(System.out::println);

        // lines() reads all lines
        Files.lines(Paths.get(filePath)).forEach(System.out::println);


        List<String> strings = Files.readAllLines(Paths.get(filePath));
        strings.forEach(System.out::println);

        break;

      } catch (IOException e) {
        //System.out.println(e);
        System.out.println("File Path is not Valid");
      }

    }
  }

  // Checked Exception
  public static void ex2() {
    try {
      Path sourcePath = Paths.get("source/java_logo.png");
      Path destinationPath = Paths.get("destination/new_java_logo.png");
      if (Files.exists(sourcePath) && Files.isRegularFile(sourcePath)) {
        Files.copy(
                sourcePath,
                destinationPath,
                StandardCopyOption.REPLACE_EXISTING
        );
      } else {
        System.out.println("source file is not valid");
      }

    } catch (IOException e) {
      System.out.println(e);
      //System.out.println(e.getMessage());
      //e.printStackTrace();
    }

  }

  // Unchecked Exception
  public static void ex3() {
    int[] numbers = {4, 5, 8, 9}; // 4

    try {
      System.out.println(numbers[0]);
      System.out.println(numbers[1]);
      // System.out.println(numbers[4]); // it throws ArrayIndexOutOfBoundsException

      String text = null;
      //System.out.println(text.toUpperCase()); // it throws NullPointerException

      LocalDate localDate = LocalDate.parse("2020-1-1"); // YYYY-MM-DD

    /*} catch (ArrayIndexOutOfBoundsException e) {
      System.out.println(e);
    } catch (NullPointerException e) {
      System.out.println("Text was null");
    } catch (DateTimeParseException e) {
      System.out.println(e.getMessage());
    }*/

    } catch (ArrayIndexOutOfBoundsException | NullPointerException | DateTimeParseException e) {
      System.out.println(e);
    }
    System.out.println("Done");


  }

  // throw is used to throw an exception explicitly
  // it is  concept of exception handling in Java
  public static String ex4() {

    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter a valid student id (A-1234) : ");
    String studentId = scanner.nextLine();

    if (studentId.length() == 0) {
      throw new IllegalArgumentException("student id was null");
    }

    if (studentId.length() != 6) {
      throw new IllegalArgumentException("student id length was not valid");
    }

    if (!studentId.startsWith("A")) {
      throw new IllegalArgumentException("student id must start with A");
    }

    return studentId;

  }

  public static void ex5() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter a number: ");
    double n1 = scanner.nextDouble();
    System.out.println("Enter a number: ");
    double n2 = scanner.nextDouble();

    if (n2 == 0) {
      throw new IllegalArgumentException("second number must not be zero");
    }

    double result = n1 / n2;
    System.out.println(result);
  }

  // custom exception
  public static void ex6() throws DataNotFoundException {
    List<String> names = Arrays.asList("Mehrdad", "Marcus", "Simon", "Åsa");

    String inputName = "Test";
    Optional<String> optional = names.stream()
            .filter(name -> name.equalsIgnoreCase(inputName))
            .findFirst();
    if (optional.isPresent()) System.out.println(optional.get());
    else throw new DataNotFoundException("data not found", 1, inputName);
  }

  public static void ex7() throws InsufficientFoundsException {
    double balance = 100;
    System.out.println("Current Balance is : " + balance);
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter a valid number: ");
    double amount = scanner.nextDouble();

    if (amount > balance) {
      throw new InsufficientFoundsException("balance is insufficient");
    }

    balance = balance - amount;
    System.out.println(balance);

  }


  // finally-block
  public static void ex8() {
    BufferedWriter writer = null;
    try {
      // [                     ]
      Path path = Paths.get("dir/skills.txt");
      writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND);
      writer.newLine();
      writer.append("Test2");
    } catch (IOException e) {
      System.out.println(e);
    } finally {
      System.out.println("-- finally block --");
      try {
        if (writer != null) {
          writer.close();
        }
      } catch (IOException e) {
        System.out.println(e);
      }
    }

  }

  // try-with-resources
  public static void ex9() {
    Path path = Paths.get("dir/skills.txt");
    try (
            BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND);
    ) {
      writer.newLine();
      writer.append("Test3");
    } catch (IOException e) {
      System.out.println(e);
    }
  }

}
