/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.achermida.darefilereader;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

/**
 *
 * @author achermida
 */
@Singleton
public class DareReaderBean {

    private static final String ARQUIVO_LEITURA = "/home/achermida/dev/darespagas.txt";
    private static final Path fFilePath = Paths.get(ARQUIVO_LEITURA);
    private final static Charset ENCODING = StandardCharsets.UTF_8;

    @Schedule(second = "*/10", minute = "*", hour = "*", persistent = false)
    public void leArquivo() {
        System.out.println("Executou : ");
        try (Scanner scanner = new Scanner(fFilePath, ENCODING.name())) {
            while (scanner.hasNextLine()) {
                processLine(scanner.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void processLine(String aLine) {
        try {
            Scanner scanner = new Scanner(aLine);
            scanner.useDelimiter("\\|");
            if (scanner.hasNext()) {
                String codigo = scanner.next();
                String value = scanner.next();
                String value2 = scanner.next();
                String value3 = scanner.next();
                log("Name is : " + codigo.trim() + ", and Value is : " + value.trim());
            } else {
                log("Linha vazia ou inválida: " + aLine);
            }
        } catch (Exception e) {
            log("Linha inválida: " + aLine);
        }
    }

    private void log(String texto) {
        System.out.println(texto);
    }
    
//   Se usarmos Java 8 algum dia ... 
//		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
//
//			stream.forEach(System.out::println);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

}
