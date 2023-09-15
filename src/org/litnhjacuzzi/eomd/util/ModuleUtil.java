package org.litnhjacuzzi.eomd.util;

import java.io.File;

public class ModuleUtil 
{
	public static File getFileByName(File dir, String name) {
		File[] result = dir.listFiles((_dir, _name) -> _name.equals(name));
		if(result.length == 0) return null;
		return result[0];
	}
}
