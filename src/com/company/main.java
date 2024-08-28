package com.company;

import java.util.List;
import java.util.Optional;

public class main {
    public static void main(String[] args) {
        // Create simple folders
        Folder folder1 = new SimpleFolder("Documents", "SMALL");
        Folder folder2 = new SimpleFolder("Downloads", "MEDIUM");
        Folder folder3 = new SimpleFolder("Photos", "LARGE");

        // Create a complex folder and add some folders to it
        ComplexFolder complexFolder = new ComplexFolder("Archives", "MEDIUM");
        complexFolder.addFolder(new SimpleFolder("Old Documents", "SMALL"));
        complexFolder.addFolder(new SimpleFolder("Old Photos", "LARGE"));

        // Create another complex folder with nested folders
        ComplexFolder nestedComplexFolder = new ComplexFolder("Projects", "LARGE");
        nestedComplexFolder.addFolder(new SimpleFolder("Project A", "SMALL"));
        nestedComplexFolder.addFolder(new SimpleFolder("Project B", "MEDIUM"));
        nestedComplexFolder.addFolder(complexFolder); // Add the previous complex folder as a subfolder

        // Add all folders to the FileCabinet
        FileCabinet fileCabinet = new FileCabinet(List.of(folder1, folder2, folder3, nestedComplexFolder));

        // Test findFolderByName
        System.out.println("Testing findFolderByName:");
        Optional<Folder> foundFolder = fileCabinet.findFolderByName("Project A");
        System.out.println(foundFolder.isPresent() ? "Found: " + foundFolder.get().getName() : "Folder not found");

        foundFolder = fileCabinet.findFolderByName("Old Photos");
        System.out.println(foundFolder.isPresent() ? "Found: " + foundFolder.get().getName() : "Folder not found");

        foundFolder = fileCabinet.findFolderByName("NonExistent");
        System.out.println(foundFolder.isPresent() ? "Found: " + foundFolder.get().getName() : "Folder not found");

        // Test findFoldersBySize
        System.out.println("\nTesting findFoldersBySize:");
        List<Folder> mediumFolders = fileCabinet.findFoldersBySize("MEDIUM");
        System.out.println("Medium folders count: " + mediumFolders.size());
        for (Folder f : mediumFolders) {
            System.out.println(" - " + f.getName());
        }

        // Test count
        System.out.println("\nTesting count:");
        int totalFolders = fileCabinet.count();
        System.out.println("Total folders count: " + totalFolders);
    }
}
