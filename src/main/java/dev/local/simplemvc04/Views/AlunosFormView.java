package dev.local.simplemvc04.Views;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import dev.local.simplemvc04.Controllers.AlunosListController;

/**
 * @author Andre Delorme
 *
 */
public class AlunosFormView {

	JFrame frame;

	public AlunosFormView(AlunosListController controller) {

		// cria componentes de interface
		JTextField raTextField = new JTextField(20);
		JTextField nameTextField = new JTextField(20);
		JTextField lastnameTextField = new JTextField(20);
		JTextField courseTextField = new JTextField(20);
		JTextField periodTextField = new JTextField(20);

		JButton saveButton = new JButton("Salvar");
		JButton cancelButton = new JButton("Cancelar");

		// botão de todos: realiza chamada ao controller
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] newData = {
						raTextField.getText(),
						nameTextField.getText(),
						lastnameTextField.getText(),
						courseTextField.getText(),
						periodTextField.getText()
				};
				controller.insertOne(newData);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		// botão de inserção: realiza chamada ao controller
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false); 
				frame.dispose();
			}
		});
		// cria JPanel para organizar os componentes (layout)
		GridLayout formLayout = new GridLayout(0, 2);
		JPanel ctrlPane = new JPanel();
		ctrlPane.setLayout(formLayout);
		// adiciona os componentes ao JPanel
		ctrlPane.add(new Label("RA:"));
		ctrlPane.add(raTextField);
		ctrlPane.add(new Label("Nome:"));
		ctrlPane.add(nameTextField);
		ctrlPane.add(new Label("Sobrenome:"));
		ctrlPane.add(lastnameTextField);
		ctrlPane.add(new Label("Curso:"));
		ctrlPane.add(courseTextField);
		ctrlPane.add(new Label("Período:"));
		ctrlPane.add(periodTextField);
		ctrlPane.add(saveButton);
		ctrlPane.add(cancelButton);

		// cria um frame para receber o split pane
		frame = new JFrame(
				"Swing MVC Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(ctrlPane);
		frame.pack();
		frame.setVisible(true);
	}

}
