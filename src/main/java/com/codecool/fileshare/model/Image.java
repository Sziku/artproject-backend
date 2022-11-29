package com.codecool.fileshare.model;


public class Image {
    //TODO
    private String id;
    private String title;
    private String owner;
    private String description;
    private byte[] content;
    private String extension;

    public Image(String id, String title, String owner, String description, byte[] content, String extension) {
        this.id = id;
        this.title = title;
        this.owner = owner;
        this.description = description;
        this.content = content;
        this.extension = extension;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOwner() {
        return owner;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getContent() {
        return content;
    }

    public String getExtension() {
        return extension;
    }
}
