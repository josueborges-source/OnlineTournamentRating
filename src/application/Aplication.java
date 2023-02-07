package application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Aplication extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aplication frame = new Aplication();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Aplication() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 286);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(120, 27, 253, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Arquivo Origem:");
		lblNewLabel.setBounds(30, 28, 80, 16);
		contentPane.add(lblNewLabel);

		JButton selecionarArquivoBotao = new JButton("Selecionar...");

		selecionarArquivoBotao.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String[] extensoes = { "xls", "xlsx" };
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", extensoes);
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File selectedFile = chooser.getSelectedFile();
					System.out.println(selectedFile.getName());

					FileInputStream fis;

					try {
						fis = new FileInputStream(selectedFile);
						HSSFWorkbook workbook = new HSSFWorkbook(fis);
						HSSFSheet sheet = workbook.getSheetAt(0);
						HSSFRow row = sheet.getRow(0);
						HSSFCell cellA1 = row.getCell(0);
						HSSFCell cellB1 = row.getCell(1);
						System.out.println("A1: " + cellA1.getStringCellValue());
						System.out.println("B1: " + cellB1.getStringCellValue());
						workbook.close();
						fis.close();
						
					} catch (FileNotFoundException exception) {
						JOptionPane.showMessageDialog(null, exception.toString());
						
					} catch (IOException exception) {
						JOptionPane.showMessageDialog(null, exception.toString());
					}
					catch (org.apache.poi.hssf.OldExcelFormatException excelFormat) {
						StringBuilder stringBuilder = new StringBuilder();
						stringBuilder.append("Formato Inválido de Arquivo Excel:");
						stringBuilder.append(" ");
						stringBuilder.append(excelFormat.getMessage());		
						JOptionPane.showMessageDialog(null, stringBuilder.toString());
					}
				}
			}
		});
		
		selecionarArquivoBotao.setBounds(383, 25, 113, 23);
		contentPane.add(selecionarArquivoBotao);

		JButton btnNewButton = new JButton("Exportar Arquivo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(231, 150, 142, 58);
		contentPane.add(btnNewButton);
	}	
	
	
}
