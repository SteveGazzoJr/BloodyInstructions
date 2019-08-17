package com.projectDAVE.BloodyInstructions;

import com.sun.tools.javac.util.Pair;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        String text = null;
        List<Pair<String, String>> replacementPairs = InitializePairList();

        System.out.println("Feed me some shakespeare yo:");
        try {
            StringBuffer stringBuffer = new StringBuffer("");
            String line = null;
            while ((line = reader.readLine()) != null) {
                // keep appending last line read to buffer
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
            text = stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        text = text.toLowerCase();
        System.out.print(ReplaceText(text, replacementPairs));
    }

    public static List<Pair<String, String>> InitializePairList(){
        List<Pair<String, String>> listOfPairs = new ArrayList<>();
        BufferedReader reader;
        String filePath = new File("").getAbsolutePath() + "/shakesPairs.txt";
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while (line != null) {
                String[] splitString = line.split(",");
                Pair<String, String> pairToAdd = new Pair<>(" " + splitString[0] +" ", " "+splitString[1]+" ");
                listOfPairs.add(pairToAdd);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listOfPairs;
    }

    public static String ReplaceText(String inputText, List<Pair<String, String>> dictionary){
        for(Pair<String, String> entry : dictionary){
            inputText = inputText.replace(entry.fst.toLowerCase(), entry.snd.toLowerCase());
        }

        return inputText;
    }
}
