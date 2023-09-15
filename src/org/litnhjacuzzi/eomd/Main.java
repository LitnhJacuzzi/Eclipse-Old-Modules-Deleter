package org.litnhjacuzzi.eomd;

import java.awt.EventQueue;

import org.litnhjacuzzi.eomd.ui.DeleterDialog;

public class Main 
{
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			DeleterDialog dialog = new DeleterDialog();
			dialog.setSize(750, 600);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		});
	}
}
