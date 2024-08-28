package com.company;

import java.util.ArrayList;
import java.util.List;

class ComplexFolder implements MultiFolder {
    private String name;
    private String size;
    private List<Folder> folders;

    public ComplexFolder(String name, String size) {
        this.name = name;
        this.size = size;
        this.folders = new ArrayList<>();
    }

    public void addFolder(Folder folder) {
        folders.add(folder);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSize() {
        return size;
    }

    @Override
    public List<Folder> getFolders() {
        return folders;
    }
}