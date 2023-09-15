package org.litnhjacuzzi.eomd.util;

import java.io.File;

public class OldModuleDirNameAnalyzer extends OldModuleAnalyzer
{
	public OldModuleDirNameAnalyzer(String eclipsePath) {
		super(eclipsePath);
	}

	@Override
	ModuleInfo resolvePlugin(File plugin) {
		String[] pluginInfo = plugin.getName().split("_", 2);
		if(pluginInfo.length < 2) {
			System.err.println("Invalid directory name: " + plugin.getName());
			return null;
		}
		
		return new ModuleInfo(plugin, pluginInfo[0], pluginInfo[1]);
	}

	@Override
	ModuleInfo resolveFeature(File feature) {
		return resolvePlugin(feature);
	}
}
