package data;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.opencsv.CSVReader;

public class TimeFrame {
	private Date begin;
	private Date end;
	private ArrayList<FinanceDatum> data;
	

	public TimeFrame(File file) {
		loadData(file);
	}
	
	public TimeFrame(File file, Date begin) {
		this.begin = begin;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 1);
		this.end = cal.getTime();
		
		loadData(file, begin, end);
	}
	
	/**
	 * Creates a new TimeFrame and loads the data from the file
	 * given by the path
	 * @param file
	 * @param begin
	 * @param end
	 */
	public TimeFrame(File file, Date begin, Date end) {
		this.begin = begin;
		this.end = end;
		loadData(file, begin, end);
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
	private void loadData(File file) {
		data = new ArrayList<FinanceDatum>(21);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		try (CSVReader reader = new CSVReader(new FileReader(file))) {      
			String[] row;
			// skip header row
			reader.readNext();
			
			// read first row to get begin date
			if ((row = reader.readNext()) != null) {
				String dateStr = row[0];
				this.begin = format.parse(dateStr);
				Calendar cal = Calendar.getInstance();
				cal.setTime(this.begin);
				cal.add(Calendar.YEAR, 1);
				
				this.end = cal.getTime();
				FinanceDatum day = new FinanceDatum(begin,
					Double.parseDouble(row[1]), Double.parseDouble(row[2]),
					Double.parseDouble(row[3]), Double.parseDouble(row[4]));
				
				data.add(day);
			}
			
			Date lastDate = this.end;
			
			while ((row = reader.readNext()) != null) {
				String dateStr = row[0];
				Date date = format.parse(dateStr);
				
				if (!date.after(this.end)) {
					lastDate = date;
					FinanceDatum day = new FinanceDatum(
						date,
						Double.parseDouble(row[1]),
						Double.parseDouble(row[2]),
						Double.parseDouble(row[3]),
						Double.parseDouble(row[4]));

					data.add(day);
				}
			}
			
			this.end = lastDate;
        } catch (Exception e) {
        		e.printStackTrace();
        }
	}
	
	private void loadData(File file, Date begin, Date end) {
		data = new ArrayList<FinanceDatum>(21);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		try (CSVReader reader = new CSVReader(new FileReader(file))) {
			String[] row;
			// skip header row
			reader.readNext();
			
			// if beginning is null, set beginning to first date
			if (this.begin == null && (row = reader.readNext()) != null) {
				String dateStr = row[0];
				this.begin = format.parse(dateStr);

				FinanceDatum day = new FinanceDatum(this.begin,
					Double.parseDouble(row[1]), Double.parseDouble(row[2]),
					Double.parseDouble(row[3]), Double.parseDouble(row[4]));
				
				data.add(day);
			}
			
			if (this.begin.after(end)) {
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.YEAR, 1);
				this.end = cal.getTime();
			}
			
			Date lastDate = this.end;
			
			while ((row = reader.readNext()) != null) {
				String dateStr = row[0];
				Date date = format.parse(dateStr);
				
				if (!date.before(this.begin) && !date.after(this.end)) {
					lastDate = date;
					FinanceDatum day = new FinanceDatum(
						date,
						Double.parseDouble(row[1]),
						Double.parseDouble(row[2]),
						Double.parseDouble(row[3]),
						Double.parseDouble(row[4]));

					data.add(day);
				}
			}
			
			this.end = lastDate;
        } catch (Exception e) {
        		e.printStackTrace();
        }
	}
}
