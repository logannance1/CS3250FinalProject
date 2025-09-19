import java.util.ArrayList;
import java.util.Date;

public class TimeFrame {
	private Date begin;
	private Date end;
	private ArrayList<FinanceData> data;
	
	public TimeFrame(Date begin, Date end, String path) {
		this.begin = begin;
		this.end = end;
	}
	

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public ArrayList<FinanceData> getData() {
		return data;
	}
	
	public void appendData(FinanceData data) {
		this.data.add(data);
	}
	
	public FinanceData removeData() {
		return this.data.removeLast();
	}
	
	private void loadData() {}
}
