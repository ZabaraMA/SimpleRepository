package twoDimensionalStructures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import java.util.Map.Entry;

public class Rows implements Iterable<Row>, RandomAccess, java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<Row> rList;
	ValueTable vt;
	
	protected Rows(ValueTable vt) {
		super();
		rList = new ArrayList<>();
		this.vt = vt;
	}

	@Override
	public Iterator<Row> iterator() {
		return rList.iterator();
	}
	
	public int size() {
		return rList.size();
	}

	public boolean isEmpty() {
		return rList.isEmpty();
	}
	
	public int add() {
		rList.add(new Row(vt));
		return rList.size();
	}
	
	public void add(int index) {
		rList.add(new Row(vt));
	}
	
	public void delete(int index) {
		Row row = rList.get(index);
		row.row = null;
		row.vtable = null;
		rList.remove(row);
	}
	
	public Row getRow(int index) {
		return rList.get(index);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < rList.size(); i++) {
			sb.append("[");
			sb.append(i);
			sb.append(rList.get(i).toString());
			sb.append("]");
			sb.append("\n");
		};
		return sb.toString();
	}
	
	
}
