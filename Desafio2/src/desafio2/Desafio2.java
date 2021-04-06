/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Eleni Oliveira
 */
public class Desafio2 {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        HashMap<Character, Character> map = new HashMap<>();
        map.put('[', ']');
        map.put('{', '}');
        map.put('(', ')');

        try {

            String saida = "";
            File file = new File("src\\input.txt");
            try (
                    Scanner sc = new Scanner(file)) {
                while (sc.hasNextLine()) {
                    String lines = sc.nextLine().trim();
                    saida += lines + " - ";
                    boolean temErro = false;
                    Stack<Character> characterStack = new Stack<>();

                    for (int i = 0; i < lines.length(); i++) {

                        if (map.containsKey(lines.charAt(i))) {
                            characterStack.push(lines.charAt(i));
                        } else {
                            if (characterStack.isEmpty()) {
                                saida += "Inválido";
                                temErro = true;
                                break;
                            }
                            char lastBracket = characterStack.pop();

                            if (map.get(lastBracket) != lines.charAt(i)) {
                                saida += "Inválido";
                                temErro = true;
                                break;
                            }
                        }
                    }
                    if (!temErro) {
                        saida += characterStack.isEmpty() ? "OK" : "Inválido";
                    }
                    saida += "\r\n";

                }
                Path pathFile = Paths.get("src\\output.txt");
                List<String> saidaList = Arrays.asList(saida);
                Files.write(pathFile, saidaList, StandardCharsets.UTF_8);
                System.out.println(saidaList);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ocorreu um erro.");

        }
    }
}
