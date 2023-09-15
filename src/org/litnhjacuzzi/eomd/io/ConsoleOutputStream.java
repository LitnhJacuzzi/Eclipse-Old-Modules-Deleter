package org.litnhjacuzzi.eomd.io;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.litnhjacuzzi.eomd.ui.AutoScrollController;

public abstract class ConsoleOutputStream extends OutputStream
{
	private final AutoScrollController autoScroller;
	private final JTextPane console;
	
	public ConsoleOutputStream(AutoScrollController autoScroller, JTextPane console) {
		this.autoScroller = autoScroller;
		this.console = console;
	}
	
	@Override
	public void write(int b) throws IOException {
		StyledDocument doc = console.getStyledDocument();
		SimpleAttributeSet style = new SimpleAttributeSet();
		StyleConstants.setForeground(style, getTextColor());
		try {
			doc.insertString(doc.getLength(), String.valueOf((char) b), style);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(() -> {
			if(autoScroller.isAutoScrollEnabled()) {
				autoScroller.autoScroll();
			}
		});
	}
	
	abstract Color getTextColor();
}
