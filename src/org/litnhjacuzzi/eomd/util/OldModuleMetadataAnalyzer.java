package org.litnhjacuzzi.eomd.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class OldModuleMetadataAnalyzer extends OldModuleAnalyzer 
{
	public OldModuleMetadataAnalyzer(File eclipseDir) {
		super(eclipseDir);
	}

	@Override
	ModuleInfo resolvePlugin(File plugin) {
		if(plugin.isDirectory()) {
			File metaInfDir = ModuleUtil.getFileByName(plugin, "META-INF");
			if(metaInfDir == null) {
				System.err.println("Directory \"META-INF\" for " + plugin.getName() + " not found.");
				return null;
			}
			
			File manifestFile = ModuleUtil.getFileByName(metaInfDir, "MANIFEST.MF");
			if(manifestFile == null) {
				System.err.println("Manifest file for " + plugin.getName() + " not found.");
				return null;
			}
			
			try {
				FileInputStream manifestReader = new FileInputStream(manifestFile);
				Attributes attributes = new Manifest(manifestReader).getMainAttributes();
				String moduleName = attributes.getValue("Bundle-SymbolicName");
				String moduleVersion = attributes.getValue("Bundle-Version");
				manifestReader.close();
				return new ModuleInfo(plugin, moduleName, moduleVersion);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}else {
			try {
				JarFile pluginFile = new JarFile(plugin);
				Attributes attributes = pluginFile.getManifest().getMainAttributes();
				String moduleName = attributes.getValue("Bundle-SymbolicName");
				String moduleVersion = attributes.getValue("Bundle-Version");
				pluginFile.close();
				return new ModuleInfo(plugin, moduleName, moduleVersion);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	@Override
	ModuleInfo resolveFeature(File feature) {
		if(feature.isDirectory()) {
			File featureXML = ModuleUtil.getFileByName(feature, "feature.xml");
			if(featureXML == null) {
				System.err.println("Feature xml file for " + feature.getName() + " not found.");
				return null;
			}
			
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(Feature.class);
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				Feature featureInfo = (Feature) unmarshaller.unmarshal(featureXML);
				return new ModuleInfo(feature, featureInfo.id, featureInfo.version);
			} catch (JAXBException e) {
				e.printStackTrace();
				return null;
			}
		}else {
			System.out.println("Skip file: " + feature);
			return null;
		}
	}
}
