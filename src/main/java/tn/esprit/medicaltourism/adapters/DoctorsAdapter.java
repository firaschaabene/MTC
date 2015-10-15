package tn.esprit.medicaltourism.adapters;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import tn.esprit.medicaltourism.delegate.DoctorServiceDelegate;
import tn.esprit.medicaltourism.domain.Doctor;

public class DoctorsAdapter extends AbstractTableModel {
	/*
	 * String sector;
	 */
	List<Doctor> doctors = new ArrayList<Doctor>();

	String[] headers = { "Id", "LastName", "FirstName", "NickName",
			"Speciality" ,"Email"};

	public DoctorsAdapter() {
		doctors = DoctorServiceDelegate.findAllDoctors();

	}

	@Override
	public int getRowCount() {
		return doctors.size();
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
			return doctors.get(rowIndex).getId();
		case 1:
			return doctors.get(rowIndex).getLastName();
		case 2:
			return doctors.get(rowIndex).getFirstName();
		case 3:
			return doctors.get(rowIndex).getNickName();
		case 4:
			return doctors.get(rowIndex).getSpeciality();
		case 5:
			return doctors.get(rowIndex).getEmail();
		default:
			return null;
		}
	}
}
