package org.litnhjacuzzi.eomd.io;

import java.awt.Color;

import javax.swing.JTextPane;

import org.litnhjacuzzi.eomd.ui.AutoScrollController;

public class ConsoleErrorOutputStream extends ConsoleOutputStream
{
	public ConsoleErrorOutputStream(AutoScrollController autoScroller, JTextPane console) {
		super(autoScroller, console);
	}

	@Override
	Color getTextColor() {
		return Color.red;
	}
}
