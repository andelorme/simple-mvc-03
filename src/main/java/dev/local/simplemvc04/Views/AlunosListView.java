package dev.local.simplemvc04.Views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import dev.local.simplemvc04.Controllers.AlunosListController;

/**
 * @author Andre Delorme
 *
 */
public class AlunosListView {

	public AlunosListView(AlunosListController controller) {

		// cria componentes de interface
		JTextField searchTermTextField = new JTextField(20);
		JButton filterButton = new JButton("Filtrar");
		JButton insertOneButton = new JButton("Insere Um");
		JButton allButton = new JButton("Listar Todos");
		JButton deleteButton = new JButton("Apagar");
		JButton updateButton = new JButton("Altera Um");
		JTable table = new JTable();

		// associa o modelo do controller a tabela da view
		table.setModel(controller.getModel());

		// botão de filtro: realiza chamada ao controller
		filterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = searchTermTextField.getText();
				if (text.isEmpty())
					JOptionPane.showMessageDialog(null,
							"Digite um termo para busca", "Erro",
							JOptionPane.ERROR_MESSAGE);
				else
					controller.filterData(text);
			}
		});
		// botão de todos: realiza chamada ao controller
		allButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.fetchAll();
			}
		});
		// botão de inserção: realiza chamada ao controller
		insertOneButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 new AlunosFormView(controller);
			}
		});
		// botão de deleção: realiza chamada ao controller
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null,
							"Nehum aluno selecionado.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else
					controller.deleteOne(table.getValueAt(row, 0));
			}
		});
		// botão de atualização: realiza chamada ao controller
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null,
							"Nehum aluno selecionado.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Object[] obj = {
							table.getValueAt(row, 0),
							table.getValueAt(row, 1) + " de Fátima",
							table.getValueAt(row, 2),
							table.getValueAt(row, 3),
							table.getValueAt(row, 4) };
					controller.updateOne(table.getValueAt(row, 0), obj);
				}
			}
		});

		// cria JPanel para organizar os componentes (layout)
		JPanel ctrlPane = new JPanel();

		// adiciona os componentes ao JPanel
		ctrlPane.add(searchTermTextField);
		ctrlPane.add(filterButton);
		ctrlPane.add(allButton);
		ctrlPane.add(insertOneButton);
		ctrlPane.add(deleteButton);
		ctrlPane.add(updateButton);

		// JScrollPane pane com borda e título para a tabela
		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setPreferredSize(new Dimension(600, 200));
		tableScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				"Lista de Alunos", TitledBorder.CENTER, TitledBorder.TOP));

		// split pane vertical com os controles em cima e a tabela embaixo
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				ctrlPane, tableScrollPane);
		splitPane.setEnabled(false);

		// cria um frame para receber o split pane
		JFrame frame = new JFrame(
				"Swing MVC Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(splitPane);
		frame.pack();
		frame.setVisible(true);
	}

}
