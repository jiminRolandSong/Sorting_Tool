package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;



public class Main {
    public static void doLong(Scanner scanner){
        List<Long> list = new ArrayList<>();
        Map<Long, Integer> frequencyMap = new HashMap<>();
        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();
            list.add(number);
        }
        for (Long l : list){
            frequencyMap.put(l, frequencyMap.getOrDefault(l,0) + 1);
        }
        System.out.printf("Total numbers: %d.\n", list.size());


        Comparator<Long> Comparator = java.util.Comparator.comparingInt((Long m) -> frequencyMap.get(m)).thenComparingLong(m -> m);
        list.sort(Comparator);
        List<Long> newList = new ArrayList<>();
        for (long l : list){
            if (!newList.contains(l)){
                newList.add(l);
            }
        }
        for (long s : newList){
            int y = frequencyMap.get(s);
            System.out.printf("%d: %d time(s), %d%s.\n", s, y, (y * 100 / list.size()), "%");
        }

    }
    public static void doLine(Scanner scanner){
        List<String> list = new ArrayList<>();
        Map<String, Integer> frequencyMap = new HashMap<>();
        while (scanner.hasNextLine()) {
            String number = scanner.nextLine();
            list.add(number);
        }
        for (String l : list){
            frequencyMap.put(l, frequencyMap.getOrDefault(l,0) + 1);
        }
        System.out.printf("Total lines: %d.\n", list.size());


        Comparator<String> Comparator = java.util.Comparator.comparingInt((String m) -> frequencyMap.get(m)).thenComparing(m -> m);
        list.sort(Comparator);
        List<String> newList = new ArrayList<>();
        for (String l : list){
            if (!newList.contains(l)){
                newList.add(l);
            }
        }
        for (String s : newList){
            int y = frequencyMap.get(s);
            System.out.printf("%s: %d time(s), %d%s.\n", s, y, (y * 100 / list.size()), "%");
        }
    }
    public static void doWord(Scanner scanner){
        List<String> list = new ArrayList<>();
        Map<String, Integer> frequencyMap = new HashMap<>();
        while (scanner.hasNext()) {
            String number = scanner.next();
            list.add(number);
        }
        for (String l : list){
            frequencyMap.put(l, frequencyMap.getOrDefault(l,0) + 1);
        }
        System.out.printf("Total words: %d.\n", list.size());


        Comparator<String> Comparator = java.util.Comparator.comparingInt((String m) -> frequencyMap.get(m)).thenComparing(m -> m);
        list.sort(Comparator);
        List<String> newList = new ArrayList<>();
        for (String l : list){
            if (!newList.contains(l)){
                newList.add(l);
            }
        }
        for (String s : newList){
            int y = frequencyMap.get(s);
            System.out.printf("%s: %d time(s), %d%s.\n", s, y, (y * 100 / list.size()), "%");
        }
    }

    public static void doSortNum(Scanner scanner){
        List<Integer> list = new ArrayList<>();
        while (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            list.add(number);
        }
        int total = list.size();
        System.out.println("Total numbers: " + total);
        Collections.sort(list);
        System.out.print("Sorted data:");
        for (int i : list){
            System.out.print(" " + i);
        }
    }

    public static void doSortLine(Scanner scanner){
        List<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            list.add(line);
        }
        int total = list.size();
        System.out.println("Total lines: " + total);
        Collections.sort(list);
        System.out.print("Sorted data:");
        for (String i : list){
            System.out.println(i);
        }
    }
    public static void doSortWord(Scanner scanner){
        List<String> list = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.next();
            list.add(line);
        }
        int total = list.size();
        System.out.println("Total words: " + total);
        Collections.sort(list);
        System.out.print("Sorted data:");
        for (String i : list){
            System.out.println(i);
        }
    }

    public static void sortTool(String sortType, String what, String input, String output){
        File file;
        Scanner scanner = new Scanner(System.in);
        if (input != null){
            file = new File(input);
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        if (output != null){
            try {
                System.setOut(new PrintStream(new File(output)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (Objects.equals(sortType,"natural")){
            switch (what) {
                case "long" -> doSortNum(scanner);
                case "line" -> doSortLine(scanner);
                case "word" -> doSortWord(scanner);
            }
        } else if (Objects.equals(sortType,"byCount")) {
            switch (what) {
                case "long" -> doLong(scanner);
                case "line" -> doLine(scanner);
                case "word" -> doWord(scanner);
            }
        }
        scanner.close();
    }


    public static void main(final String[] args) {
        String sortType = "natural";
        String what = "word";
        int index = 0;
        String input = null;
        String output = null;
        for (String arg : args) {
            switch (arg) {
                case "-sortingType" -> {
                    try{
                        sortType = args[index + 1];
                    }catch (Exception e){
                        System.out.println("No sorting type defined");
                    }
                }
                case "-dataType" -> {
                    try{
                        what = args[index + 1];
                    }catch (Exception e){
                        System.out.println("No data type defined");
                    }
                }
                case "-inputFile" -> {
                    try{
                        input = args[index + 1];
                    }catch (Exception e){
                        System.out.println("No input file defined");
                    }
                }
                case "-outputFile" -> {
                    try{
                        output = args[index + 1];
                    }catch (Exception e){
                        System.out.println("No output file defined");
                    }
                }
                default -> {
                    if (arg.startsWith("-")){
                        System.out.printf("\"%s\" is not a valid parameter. It will be skipped.",arg);
                    }
                }
            }
            index += 1;
        }
        sortTool(sortType, what, input, output);
    }
}
