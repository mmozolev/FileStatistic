package fileStatistic;

import java.io.*;
import java.util.*;


public class FileStatistic {

    /*public static void main(String[] args) {
        printMaxFreqWord();
    }*/

    public void printMaxFreqWord() {

        HashMap<String, Integer> map = new LinkedHashMap<>();
        int maxFreq = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader;
        String buffer;
        StringBuilder stringBuilder = new StringBuilder();
        String text;

        //открываем файл
        while (true) {
            try {
                System.out.print("Enter path to file: ");
                fileReader = new BufferedReader(new FileReader(bufferedReader.readLine()));
                break;
            } catch (FileNotFoundException e1) {
                System.out.println("File not found! Try again");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally { //закрываем поток
                try {
                    bufferedReader.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //считываем построчно в билдер
        try {
            while ((buffer = fileReader.readLine()) != null) {
                stringBuilder.append(buffer);
                stringBuilder.append(" ");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //закрываем поток
        try {
        fileReader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //преобразовываем и сортируем
        text = stringBuilder.toString().toLowerCase();
        text = text.replaceAll("[^a-zа-яё0-9]+ ", " ");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(text.split(" ")));
        Collections.sort(list);

        //заполняем мапу
        for (String word : list) {
            if (!map.containsKey(word)) map.put(word, 1);
            else {
                for (Map.Entry<String, Integer> pair : map.entrySet()) {
                    if (word.equals(pair.getKey())) pair.setValue(pair.getValue() + 1);
                }
            }
        }

        //находим максимальную частоту
        for (Map.Entry<String, Integer> pair : map.entrySet()) {
            if (pair.getValue() > maxFreq) maxFreq = pair.getValue();
        }

        //выводим в консоль
        if (map.containsKey("") || map.isEmpty()) System.out.println("File is empty");
        else {
            System.out.println((map.keySet()));
            for (Map.Entry<String, Integer> pair : map.entrySet()) {
                if (pair.getValue() == maxFreq)
                        System.out.println("Word: " + pair.getKey() + " Freq: " + pair.getValue());
            }
        }
    }
}

