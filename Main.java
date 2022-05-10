package com.Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
public class Main {
    public Main() throws IOException {
    }
    public static String PobierzZadanie()
    {
        System.out.println("Podaj nazwę zadania:");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        String name = scanner.nextLine();
        return name;
    }
    public static void DodajZadanie(String name)
    {
        tasks.put(tasks.size()+1, name);
    }
    public static void WyswietlZadania(){
        for (Integer task : tasks.keySet()){
            System.out.println(task.toString() + "." + tasks.get(task));
        }
    }
    public static void UsunZadanie(int task){
        tasks.remove(task);
        if(task != tasks.size()+1){
            ZaaktualizujKlucze(task);
        }
    }
    public static void ZapiszDoPliku() throws IOException {
        FileWriter writer = new FileWriter("todolist.txt");
        for(Integer task : tasks.keySet()){
            writer.write(task.toString() + tasks.get(task) + "\n");
        }
        writer.close();
    }
    public static void WczytajZPliku() throws FileNotFoundException {
        File file = new File("todolist.txt");
        Scanner scan = new Scanner(file);
        Integer num = 1;
        while(scan.hasNextLine()){
            String tmp = scan.nextLine().replaceFirst(num.toString(), "");
            tasks.put(num, tmp);
            num += 1;
        }


    }
    public static void ZaaktualizujKlucze(int task){
        int num = 1;
        for(Integer i = task+1; i <= tasks.size()+1; i++){
            String tmp = tasks.get(i);
            tasks.remove(i);
            tasks.put(i-1,tmp);
        }
    }
    public static String WyswietlPliki(File file) throws FileNotFoundException {
        HashMap<Integer, String> liniePliku = new HashMap<>();
        String plik = "";
        System.out.println("Wybierz plik");
        Scanner scan = new Scanner(file);
        int tmp = 1;
        while(scan.hasNextLine()){
            liniePliku.put(tmp, scan.nextLine());
            System.out.println(tmp + "." + scan.nextLine());
            tmp += 1;
        }
        System.out.println(tmp + ".Wyjdź");
        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();
        while(!liniePliku.containsKey(userChoice) || userChoice != liniePliku.size() + 1) {
            if (liniePliku.containsKey(userChoice)) {
                plik = liniePliku.get(userChoice);
            } else if (userChoice == liniePliku.size() + 1) {
                plik = "Wyjdź";
            }
        }
        return plik;
    }
    static HashMap<Integer,String> tasks = new HashMap<>();
    public static void main(String[] args) throws IOException {
        File file = new File("lista_plikow.txt");
        int userChoice = -1;
         Scanner scanner = new Scanner(System.in);
         while(userChoice != 3) {
             System.out.println("Wybierz opcje:\n1.Wczytaj plik\n2.Stwórz plik\n3.Wyjdź");
             userChoice = scanner.nextInt();
             switch (userChoice) {
                 case 1 -> WyswietlPliki(file);
             }

             userChoice = -1;
             WczytajZPliku();
             while (userChoice != 5) {
                 System.out.println("Wybierz opcje\n1.Dodaj zadanie\n2.Wyświetl zadania\n3.Usuń zadanie\n4.Zapisz zmiany w pliku\n5.Wyjdź");
                 userChoice = scanner.nextInt();
                 //scanner.close();
                 if (userChoice == 1) {
                     DodajZadanie(PobierzZadanie());
                 } else if (userChoice == 2) {
                     WyswietlZadania();
                 } else if (userChoice == 3) {
                     System.out.println("Podaj numer zadania do usunięcia:");
                     UsunZadanie(scanner.nextInt());
                 } else if (userChoice == 4) {
                     ZapiszDoPliku();
                 } else if (userChoice == 5) {
                     break;
                 } else {
                     System.out.println("Błąd, podaj liczbę wskazaną w interfejsie");
                 }


             }
         }
    }
}
