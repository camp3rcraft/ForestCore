package ru.forestcore.models;

import java.util.List;

public class ForestPlugin {
    private final String name;
    private final String version;
    private final String id;
    private final String author;
    private final String modrinthUrl;
    private final String githubUrl;
    private final List<String> commands;
    private boolean enabled;

    public ForestPlugin(String name, String version, String id, String author, 
                       String modrinthUrl, String githubUrl, List<String> commands) {
        this.name = name;
        this.version = version;
        this.id = id;
        this.author = author;
        this.modrinthUrl = modrinthUrl;
        this.githubUrl = githubUrl;
        this.commands = commands;
        this.enabled = true;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getModrinthUrl() {
        return modrinthUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public List<String> getCommands() {
        return commands;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
} 