package org.litnhjacuzzi.eomd.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import sun.awt.util.IdentityArrayList;

public abstract class OldModuleAnalyzer 
{
	private final File pluginsDir; 
	private final File featuresDir;
	
	final HashMap<String, ModuleInfo> detectedModules = new HashMap<>();
	final List<File> oldModules = new IdentityArrayList<>();
	
	public OldModuleAnalyzer(File eclipseDir) {
		this.pluginsDir = ModuleUtil.getFileByName(eclipseDir, "plugins");
		this.featuresDir = ModuleUtil.getFileByName(eclipseDir, "features");
	}
	
	public List<File> getOldModules() {
		if(pluginsDir != null) {
			for(File module : pluginsDir.listFiles())
				processModuleInfo(resolvePlugin(module));
			
			System.out.println("Detected " + detectedModules.size() + " plugins");
			detectedModules.clear();
		}else {
			System.err.println("Directory \"plugins\" not found.");
		}
		
		if(featuresDir != null) {
			for(File module : featuresDir.listFiles())
				processModuleInfo(resolveFeature(module));
			
			System.out.println("Detected " + detectedModules.size() + " features");
		}else {
			System.err.println("Directory \"features\" not found.");
		}
		
		return oldModules;
	}
	
	private void processModuleInfo(ModuleInfo moduleInfo) {
		if(moduleInfo == null) return;
		
		String moduleName = moduleInfo.getName();
		ModuleInfo existedModule = detectedModules.get(moduleName);
		if(existedModule == null) {
			detectedModules.put(moduleName, moduleInfo);
		}else {
			if(existedModule.isOlderThan(moduleInfo)) {
				detectedModules.put(moduleName, moduleInfo);
				oldModules.add(existedModule.getFile());
			}else {
				oldModules.add(moduleInfo.getFile());
			}
		}
	}
	
	abstract ModuleInfo resolvePlugin(File plugin);
	
	abstract ModuleInfo resolveFeature(File feature);
}
