package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

import util.ScriptHelper;

public class MuteController {

    private Process process;
    private BufferedReader reader;
    private static final String powershell = "C:\\Windows\\System32\\WindowsPowerShell\\v1.0\\powershell.exe";
    
    public MuteController() throws IOException {
    	final Path scriptPath = ScriptHelper.ensureScriptExists();
        ProcessBuilder pb = new ProcessBuilder(
        	    powershell,
        	    "-NoProfile",
        	    "-ExecutionPolicy", "Bypass",
        	    "-File", scriptPath.toString()
        	);
        pb.redirectErrorStream(true);
        process = pb.start();
        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    }

    public String pegarStatus() throws IOException {
        return reader.readLine();
    }
}
