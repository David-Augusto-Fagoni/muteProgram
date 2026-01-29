package util;

import java.io.IOException;
import java.nio.file.*;

public class ScriptHelper {
	private static final String PS_SCRIPT = """
			Add-Type @"
			using System;
			using System.Diagnostics;

			public class SpotifyHelper {
			    public static string GetStatus() {
			        foreach (var p in Process.GetProcessesByName("Spotify")) {
			            try {
			                if (!string.IsNullOrWhiteSpace(p.MainWindowTitle)) {
			                    var t = p.MainWindowTitle.Trim();
			                    if (t.Contains("-"))
			                        return "MUSIC";
			                    return "AD";
			                }
			            } catch {}
			        }
			        return "AD";
			    }
			}
			"@

			$i = 0
			while ($true) {
			    [SpotifyHelper]::GetStatus()
			    if ($i++ % 300 -eq 0) { [GC]::Collect() }
			    Start-Sleep -Milliseconds 1000
			}
			""";
    public static Path ensureScriptExists() throws IOException {
        Path dir = Paths.get(System.getProperty("user.home"), ".spotify-muter");
        Path script = dir.resolve("spotify-monitor.ps1");

        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }

        if (!Files.exists(script)) {
            Files.writeString(
                script,
                PS_SCRIPT,
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE
            );
        }

        return script;
    }
}
