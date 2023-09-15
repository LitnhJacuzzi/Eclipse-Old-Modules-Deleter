package org.litnhjacuzzi.eomd.ui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GridBagLayoutItem 
{
	private final Component c;
	private final GridBagConstraints gbc;
	
	public GridBagLayoutItem(Component c) {
		this.c = c;
		this.gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
	}
	
	public Component getComponent() {
		return c;
	}
	
	public GridBagConstraints getGridBagConstraints() {
		return gbc;
	}
	
	public GridBagLayoutItem setGridX(int gridx) {
		gbc.gridx = gridx;
		return this;
	}
	
	public GridBagLayoutItem setGridY(int gridy) {
		gbc.gridy = gridy;
		return this;
	}
	
	public GridBagLayoutItem setGridWidth(int gridwidth) {
		gbc.gridwidth = gridwidth;
		return this;
	}
	
	public GridBagLayoutItem setGridHeight(int gridheight) {
		gbc.gridheight = gridheight;
		return this;
	}
	
	public GridBagLayoutItem setWeightX(double weightx) {
		gbc.weightx = weightx;
		return this;
	}
	
	public GridBagLayoutItem setWeightY(double weighty) {
		gbc.weighty = weighty;
		return this;
	}
	
	public GridBagLayoutItem setAnchor(int anchor) {
		gbc.anchor = anchor;
		return this;
	}
	
	public GridBagLayoutItem setFill(int fill) {
		gbc.fill = fill;
		return this;
	}
	
	public GridBagLayoutItem setIpadX(int ipadx) {
		gbc.ipadx = ipadx;
		return this;
	}
	
	public GridBagLayoutItem setIpadY(int ipady) {
		gbc.ipady = ipady;
		return this;
	}
}
