package tn.esprit.medicaltourism.adapters;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import tn.esprit.medicaltourism.delegate.ConsultantServiceDelegate;
import tn.esprit.medicaltourism.domain.Consultant;

public class ConsultantAdapter extends AbstractTableModel {
	/*
	 * String sector;
	 */
	List<Consultant> consultants = new ArrayList<Consultant>();

	String[] headers = { "Id", "LastName", "FirstName", "NickName" };

	public ConsultantAdapter() {
		consultants = ConsultantServiceDelegate.findAllConsultants();

	}

	@Override
	public int getRowCount() {
		return consultants.size();
	}

	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public String getColumnName(int i) {
		return headers[i];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return consultants.get(rowIndex).getId();
		case 1:
			return consultants.get(rowIndex).getLastName();
		case 2:
			return consultants.get(rowIndex).getFirstName();
		case 3:
			return consultants.get(rowIndex).getNickName();

		default:
			return null;
		}
	}
}
