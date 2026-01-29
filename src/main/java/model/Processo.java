package model;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Processo {

    private static final Path EXE = Paths.get(System.getProperty("user.home"), ".spotify-muter","SoundVolumeView.exe");

    public void mute() throws IOException, InterruptedException {
    	new ProcessBuilder(EXE.toString(), "/Mute", "Spotify.exe").start();
    }

    public void unmute() throws IOException, InterruptedException {
    	new ProcessBuilder(EXE.toString(), "/Unmute", "Spotify.exe").start();
    }
}

