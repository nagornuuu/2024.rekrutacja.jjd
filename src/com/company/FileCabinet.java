package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileCabinet implements Cabinet {
    private List<Folder> folders;

    public FileCabinet(List<Folder> folders) {
        this.folders = folders;
    }

    @Override
    public Optional<Folder> findFolderByName(String name) {
        return findFolderByName(name, folders);
    }

    private Optional<Folder> findFolderByName(String name, List<Folder> folders) {
        for (Folder folder : folders) {
            if (folder.getName().equals(name)) {
                return Optional.of(folder);
            }
            if (folder instanceof MultiFolder) {
                Optional<Folder> found = findFolderByName(name, ((MultiFolder) folder).getFolders());
                if (found.isPresent()) {
                    return found;
                }
            }
        }
        return Optional.empty();
    }


    @Override
    public List<Folder> findFoldersBySize(String size) {
        List<Folder> result = new ArrayList<>();
        findFoldersBySize(size, folders, result);
        return result;
    }

    private  void findFoldersBySize(String size, List<Folder> folders, List<Folder> result) {
        for (Folder folder : folders) {
            if (folder.getSize().equals(size)) {
                result.add(folder);
            }
            if (folder instanceof MultiFolder) {
                findFoldersBySize(size, ((MultiFolder) folder).getFolders(), result);
            }
        }
    }

    @Override
    public int count() {
        return count(folders);
    }

    private int count(List<Folder> folders) {
        int count = 0;
        for (Folder folder : folders) {
            count++;
            if (folder instanceof MultiFolder) {
                count += count(((MultiFolder) folder).getFolders());
            }
        }
        return count;
    }
}
