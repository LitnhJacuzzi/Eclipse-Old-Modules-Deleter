package org.litnhjacuzzi.eomd.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.litnhjacuzzi.eomd.io.ConsoleErrorOutputStream;
import org.litnhjacuzzi.eomd.io.ConsolePlainOutputStream;
import org.litnhjacuzzi.eomd.util.DetectType;
import org.litnhjacuzzi.eomd.util.OldModulesDeleter;

public class DeleterDialog extends JFrame implements GridBagConstants
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4075636774555333112L;
	
	private static final DetectType[] DETECT_TYPE = 
			new DetectType[] {DetectType.METADATA, DetectType.DIR_NAME};
	
	private final JPanel operationPane;
	private final ConsoleScrollPane consoleScrollPane;
	private final JTextPane console;
	private final JPanel statusBar;
	
	private final JLabel selectPathLabel;
	private final JTextField eclipsePath;
	private final JButton selectPathButton;
	private final JComboBox<DetectType> selectDetectType;
	private final JButton deleteButton;
	
	private final JLabel progressLabel;
	
	public DeleterDialog() {
		super("Eclipse Old Module Deleter");
		this.operationPane = new JPanel();
		this.console = new JTextPane();
		this.consoleScrollPane = new ConsoleScrollPane(console);
		this.statusBar = new JPanel();
		this.selectPathLabel = new JLabel("Select Eclipse Path:");
		this.eclipsePath = new JTextField();
		this.selectPathButton = new JButton("...");
		this.selectDetectType = new JComboBox<>(DETECT_TYPE);
		this.deleteButton = new JButton("Delete");
		this.progressLabel = new JLabel(" ");
		
		System.setOut(new PrintStream(new ConsolePlainOutputStream(consoleScrollPane, console)));
		System.setErr(new PrintStream(new ConsoleErrorOutputStream(consoleScrollPane, console)));
		initializeUIElements();
		installEventListeners();
	}
	
	private void initializeUIElements() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		operationPane.setLayout(new GridBagLayout());
		createOperationComponents();
		getContentPane().add(operationPane, BorderLayout.NORTH);
		
		console.setEditable(false);
		getContentPane().add(consoleScrollPane, BorderLayout.CENTER);
		
		statusBar.setLayout(new BorderLayout());
		statusBar.add(progressLabel, BorderLayout.WEST);
		getContentPane().add(statusBar, BorderLayout.SOUTH);
	}
	
	private void createOperationComponents() {
		addOperationComponent(new GridBagLayoutItem(selectPathLabel)
				.setGridX(0).setGridY(0).setGridWidth(1).setGridHeight(1));
		
		eclipsePath.setEditable(false);
		addOperationComponent(new GridBagLayoutItem(eclipsePath)
				.setGridX(1).setGridY(0).setGridWidth(1).setGridHeight(1).setWeightX(1).setFill(BOTH));

		addOperationComponent(new GridBagLayoutItem(selectPathButton)
				.setGridX(2).setGridY(0).setGridWidth(1).setGridHeight(1).setFill(BOTH));
		
		addOperationComponent(new GridBagLayoutItem(selectDetectType)
				.setGridX(0).setGridY(1).setGridWidth(3).setGridHeight(1).setFill(BOTH));
		
		addOperationComponent(new GridBagLayoutItem(deleteButton)
				.setGridX(1).setGridY(2).setGridWidth(2).setGridHeight(1).setAnchor(EAST));
	}
	
	private void addOperationComponent(GridBagLayoutItem item) {
		operationPane.add(item.getComponent(), item.getGridBagConstraints());
	}
	
	private void installEventListeners() {
		selectPathButton.addActionListener(e -> openDirectorySelectDialog());
		deleteButton.addActionListener(e -> {
			selectPathButton.setEnabled(false);
			deleteButton.setEnabled(false);
			deleteButton.setText("Deleting...");
			new Thread(() -> {
				OldModulesDeleter.deleteOldModules(this, eclipsePath.getText(), getDetectType());
				EventQueue.invokeLater(() -> {
					selectPathButton.setEnabled(true);
					deleteButton.setText("Delete");
					deleteButton.setEnabled(true);
				});
			}).start();
		});
	}
	
	private void openDirectorySelectDialog() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if(fileChooser.showDialog(this, "Select Directory") == JFileChooser.APPROVE_OPTION) {
			eclipsePath.setText(fileChooser.getSelectedFile().getAbsolutePath());
		}
	}
	
	public void updateDeleteProgress(String status) {
		progressLabel.setText(status);
	}
	
	private DetectType getDetectType() {
		return (DetectType) selectDetectType.getSelectedItem();
	}
}
