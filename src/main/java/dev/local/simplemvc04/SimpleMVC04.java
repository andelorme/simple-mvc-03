package dev.local.simplemvc04;

import javax.swing.SwingUtilities;

import dev.local.simplemvc04.Controllers.AlunosListController;
import dev.local.simplemvc04.Views.AlunosListView;

/**
 *
 * @author andre
 */
public class SimpleMVC04 {

	public static void main(String[] args) {
		// realiza a chamada pela thread de eventos de interface
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					initGui();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void initGui() throws Exception {
		// Cria controller
		AlunosListController controller = new AlunosListController();
		// cria view associada ao controller
		new AlunosListView(controller);
		// solicita ao controller carregar todos os itens
		controller.fetchAll();
	}
}
