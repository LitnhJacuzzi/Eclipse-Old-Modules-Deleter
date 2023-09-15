package org.litnhjacuzzi.eomd.util;

import java.io.File;
import java.util.List;

import javax.swing.JOptionPane;

import org.litnhjacuzzi.eomd.ui.DeleterDialog;

public class OldModulesDeleter 
{
	private static File eclipseDir;
	
	public static void deleteOldModules(DeleterDialog deleterDialog, 
			String eclipsePath, DetectType detectType) {
		if(!verifyEclipsePath(eclipsePath)) {
			JOptionPane.showMessageDialog(deleterDialog, 
					"Invalid eclipse path.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		deleterDialog.updateDeleteProgress("Scanning old modules");
		System.out.println("Scanning old modules...");
		
		List<File> oldModules = null;
		switch(detectType) {
			case METADATA:
				oldModules = new OldModuleMetadataAnalyzer(eclipseDir).getOldModules();
				break;
			case DIR_NAME:
				JOptionPane.showMessageDialog(deleterDialog, 
						"Not support yet.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
//				oldModules = new OldModuleDirNameAnalyzer(eclipseDir).getOldModules();
//				break;
		}
		
		int total = oldModules.size(), deletedCount = 0;
		System.out.println("Found " + total + " old modules.");
		deleterDialog.updateDeleteProgress("Deleted: 0/" + total + " modules");
		for(File module : oldModules) {
			deleteRecursively(module);
			deletedCount++;
			System.out.println("Delete: " + module.getName());
			deleterDialog.updateDeleteProgress("Deleteed :" + 
					deletedCount + "/" + total + " modules");
		}
		
		deleterDialog.updateDeleteProgress("Completed");
	}
	
	private static boolean verifyEclipsePath(String eclipsePath) {
		eclipseDir = new File(eclipsePath);
		if(!eclipseDir.exists()) 
			return false;
		return true;
	}
	
	private static void deleteRecursively(File file) {
		if(file.isDirectory()) {
			for(File subFile : file.listFiles()) {
				deleteRecursively(subFile);
			}
		}
		file.delete();
	}
}
