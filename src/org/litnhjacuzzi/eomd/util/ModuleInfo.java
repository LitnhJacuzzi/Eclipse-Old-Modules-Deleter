package org.litnhjacuzzi.eomd.util;

import java.io.File;

import org.apache.maven.artifact.versioning.DefaultArtifactVersion;

public class ModuleInfo 
{
	private final File file;
	private final String name;
	private final String version;
	
	public ModuleInfo(File file, String name, String version) {
		this.file = file;
		this.name = name;
		this.version = version;
	}
	
	public File getFile() {
		return file;
	}
	
	public String getName() {
		return name;
	}
	
	public String getVersion() {
		return version;
	}
	
	/**
	 * Determine whether this plugin is older than {@code anotherPlugin}.
	 */
	public boolean isOlderThan(ModuleInfo anotherPlugin) {
		DefaultArtifactVersion thisVersion = new DefaultArtifactVersion(version);
		DefaultArtifactVersion thatVersion = new DefaultArtifactVersion(anotherPlugin.version);
		return thisVersion.compareTo(thatVersion) < 0;
	}
}
