package data;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.opencsv.CSVReader;

public class TimeFrame {
	private Date begin;
	private Date end;
	private ArrayList<FinanceDatum> data;
	
	/**
	 * Creates a new TimeFrame and loads the data from the file
	 * given by the path
	 * @param begin
	 * @param end
	 * @param path
	 */
	public TimeFrame(Date begin, Date end, String path) {
		this.begin = begin;
		this.end = end;
		loadData(path);
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

	public ArrayList<FinanceDatum> getData() {
		return data;
	}
	
	public void appendDatum(FinanceDatum data) {
		this.data.add(data);
	}
	
	public FinanceDatum removeDatum() {
		return this.data.removeLast();
	}
	
	/**
	 * Uses the OpenCSV library to read a csv file into
	 * a list of FinanceData based on the begin and end dates
	 * @param path
	 */
	private void loadData(String path) {
		data = new ArrayList<FinanceDatum>(21);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		try (CSVReader reader = new CSVReader(new FileReader(path))) {
			String[] row;
			// skip header row
			reader.readNext();
			
			while ((row = reader.readNext()) != null) {
				String dateStr = row[0];
				Date date = format.parse(dateStr);
				
				if (!date.before(begin) && !date.after(end)) {
					FinanceDatum day = new FinanceDatum(
						date,
						Double.parseDouble(row[1]),
						Double.parseDouble(row[2]),
						Double.parseDouble(row[3]),
						Double.parseDouble(row[4]));

					data.add(day);
				}
			}
        } catch (Exception e) {
        		e.printStackTrace();
        }
	}
}
