package org.litnhjacuzzi.eomd.io;

import java.awt.Color;

import javax.swing.JTextPane;

import org.litnhjacuzzi.eomd.ui.AutoScrollController;

public class ConsolePlainOutputStream extends ConsoleOutputStream
{
	public ConsolePlainOutputStream(AutoScrollController autoScroller, JTextPane console) {
		super(autoScroller, console);
	}

	@Override
	Color getTextColor() {
		return Color.black;
	}
}
