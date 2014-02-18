package hr.fer.zemris.ecf.gui.chart;

import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Model for chart table.
 * @author Domagoj Stanković
 * @version 1.0
 */
public class ChartTableModel extends DefaultTableModel implements TableModel {

	private static final long serialVersionUID = 1L;

	private int row;
	private int col;
	private Object[][] data;
	private List<String> colNames;
	
	/**
	 * @param row Number of rows
	 * @param col Number of columns
	 * @param colNames List with names of columns
	 */
	public ChartTableModel(int row, int col, List<String> colNames) {
		data = new Object[row][col];
		this.row = row;
		this.col = col;
		this.colNames = colNames;
	}
	
	@Override
	public int getRowCount() {
		return row;
	}

	@Override
	public int getColumnCount() {
		return col;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		data[rowIndex][columnIndex] = aValue;
	}
	
	@Override
	public String getColumnName(int column) {
		return colNames.get(column);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

}
