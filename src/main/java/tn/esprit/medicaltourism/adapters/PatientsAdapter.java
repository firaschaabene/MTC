package tn.esprit.medicaltourism.adapters;


import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import tn.esprit.medicaltourism.delegate.PatientServiceDelegate;
import tn.esprit.medicaltourism.domain.Patient;

public class PatientsAdapter extends AbstractTableModel {
	/*
	 * String sector;
	 */
	List<Patient> patients = new ArrayList<Patient>();

	String[] headers = { "Id", "LastName", "FirstName", "NickName" };

	public PatientsAdapter() {
		patients = PatientServiceDelegate.findAllPatient();

	}

	@Override
	public int getRowCount() {
		return patients.size();
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
			return patients.get(rowIndex).getId();
		case 1:
			return patients.get(rowIndex).getLastName();
		case 2:
			return patients.get(rowIndex).getFirstName();
		case 3:
			return patients.get(rowIndex).getNickName();
		case 4:
			// return doctors.get(rowIndex).getSpeciality();
		default:
			return null;
		}
	}
}
