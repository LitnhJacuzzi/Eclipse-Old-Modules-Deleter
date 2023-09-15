package org.litnhjacuzzi.eomd.ui;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class ConsoleScrollPane extends JScrollPane implements AutoScrollController 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7009273687412061505L;

	public ConsoleScrollPane(JTextPane console) {
		super(console);
	}

	@Override
	public void autoScroll() {
		JScrollBar scrollBar = getVerticalScrollBar();
		scrollBar.setValue(scrollBar.getMaximum());
	}

	@Override
	public boolean isAutoScrollEnabled() {
		return true;
	}
}
