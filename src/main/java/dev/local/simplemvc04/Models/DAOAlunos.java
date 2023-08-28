package dev.local.simplemvc04.Models;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.DefaultTableModel;

/**
 * @author Andre Delorme
 * 
 */
public class DAOAlunos extends DefaultTableModel {

	// campos do modelo
	private static final Object[] FIELDS = { "RA", "Nome", "Sobrenome",
			"Curso", "Semestre" };

	// lista de alunos (modelos)
	private static final Object[][] DATA = {
			{ 5730735, "José", "Silva", "ADS", 4 },
			{ 5743313, "Maria", "Pereira", "PSICO", 2 },
			{ 7813423, "Sandra", "Rodrigues", "ADS", 4 },
			{ 3385166, "Márcio", "Fonseca", "VET", 4 },
			{ 455591, "João", "Santos", "ADS", 2 },
			{ 5555353, "Rodrigo", "Oliveira", "PSICO", 1 },
			{ 893125, "José", "Souza", "ADS", 1 },
			{ 2824896, "Arthur", "Pereira", "PSICO", 4 },
			{ 5093026, "Dalva", "Alvas", "ADS", 2 },
			{ 6301789, "Paulo", "Santos", "VET", 3 }
	};

	private ArrayList<Object> data = new ArrayList<Object>(Arrays.asList(DATA));

	// retorna os campos (pra montagem da tabela, no caso)
	public static Object[] getHeaders() {
		return FIELDS;
	}

	// retorna todos os alunos
	public Object[][] getAll() {
		int idx = 0;
		Object[][] newData = new Object[this.data.size()][];
		for (Object o : this.data)
			newData[idx++] = (Object[]) o;
		return newData;
	}

	// retorna o aluno por id
	public Object[] getById(Object id) {
		Object found = null;
		for (Object o : this.data) {
			if (((Object[]) o)[0] == id) {
				found = o;
			}
		}
		return (Object[]) found;
	}

	// insere um elemento na lista
	public void insert(Object[] data) {
		this.data.add(data);
	}

	// deleta um elemento buscando pelo primeiro campo (id)
	public void update(Object id, Object data) {
		for (Object o : this.data) {
			if (((Object[]) o)[0] == id) {
				this.data.set(this.data.indexOf(o),data);
			}
		}
		
	}

	// deleta um elemento buscando pelo primeiro campo (id)
	public void delete(Object id) {
		Object found = null;
		for (Object o : this.data) {
			if (((Object[]) o)[0] == id) {
				found = o;
			}
		}
		data.remove(found);
	}

	// executa filtro no modelo
	public Object[][] filterData(String searchTerm) {
		int idx = 0;
		Object[][] newData = new Object[this.data.size()][];
		for (Object o : this.data) {
			if ("*".equals(searchTerm.trim())) {
				newData[idx++] = (Object[]) o;
			} else {
				if (String.valueOf(((Object[]) o)[0]).startsWith(searchTerm.toUpperCase().trim())) {
					newData[idx++] = (Object[]) o;
				}
			}
		}
		return newData;
	}
}
