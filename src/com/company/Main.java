package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress save1 = new GameProgress(100, 320, 1, 0.0);
        GameProgress save2 = new GameProgress(50, 100, 5, 302.64);
        GameProgress save3 = new GameProgress(85, 320, 10, 848.59);
        saveGame("K://Games13//savegames//save1.dat", save1);
        saveGame("K://Games13//savegames//save2.dat", save2);
        saveGame("K://Games13//savegames//save3.dat", save3);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("K://Games13//savegames//save1.dat");
        arrayList.add("K://Games13//savegames//save2.dat");
        arrayList.add("K://Games13//savegames//save3.dat");
        zipFiles("K://Games13//savegames//zip.zip", arrayList);
        File game1Dat = new File("K://Games13//savegames//save1.dat");
        File game2Dat = new File("K://Games13//savegames//save2.dat");
        File game3Dat = new File("K://Games13//savegames//save3.dat");
        if (game1Dat.delete()) System.out.println("Файл \"save1.dat\" удален");
        if (game2Dat.delete()) System.out.println("Файл \"save2.dat\" удален");
        if (game3Dat.delete()) System.out.println("Файл \"save3.dat\" удален");
    }

    private static void saveGame(String path, GameProgress game) {
        try (FileOutputStream saveG = new FileOutputStream(path);
             ObjectOutputStream record = new ObjectOutputStream(saveG)) {
            record.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(String path, List<String> arrayList) {
        try (ZipOutputStream archiving = new ZipOutputStream(new FileOutputStream(path))) {
            for (String arr : arrayList) {
                try (FileInputStream fis = new FileInputStream(arr)) {
                    ZipEntry entry = new ZipEntry(arr);
                    archiving.putNextEntry(entry);
                    while (fis.available() > 0) {
                        archiving.write(fis.read());
                    }
                    archiving.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
