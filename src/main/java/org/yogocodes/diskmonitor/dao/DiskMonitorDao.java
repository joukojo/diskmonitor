package org.yogocodes.diskmonitor.dao;

import static org.yogocodes.diskmonitor.Constants.DAY_FORMAT;
import static org.yogocodes.diskmonitor.Constants.HOUR_FORMAT;
import static org.yogocodes.diskmonitor.Constants.MONTH_FORMAT;
import static org.yogocodes.diskmonitor.Constants.YEAR_FORMAT;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.yogocodes.diskmonitor.service.MonitorEvent;

@Repository("diskMonitorDao")
public class DiskMonitorDao {


	private final Logger logger = LoggerFactory.getLogger(DiskMonitorDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<MonitorEntity> getMonitorEvents(int page, int size) {

		String sql = "select * from monitor_results order by created desc limit ?,?";
		Object args[] = { page * size, size };

		RowMapper<MonitorEntity> rowMapper = new RowMapper<MonitorEntity>() {

			@Override
			public MonitorEntity mapRow(ResultSet rs, int row)
					throws SQLException {
				MonitorEntity monitorEntity = new MonitorEntity();
				monitorEntity.setId(rs.getLong("id"));
				monitorEntity.setExecutionTime(rs.getLong("response_time"));
				monitorEntity.setFileSize(rs.getLong("file_size"));
				Timestamp timestamp = rs.getTimestamp("created");
				Date created = new Date(timestamp.getTime());
				monitorEntity.setCreated(created);

				return monitorEntity;
			}
		};

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		List<MonitorEntity> result = jdbcTemplate.query(sql, args, rowMapper);
		stopWatch.stop();

		if (stopWatch.getTime() > 500L) {
			logger.warn("The paging query '{}' took over {} msecs", sql,
					stopWatch.getTime());
		}

		return result;
	}

	public int save(MonitorEvent monitorEvent) {
		MonitorEntity entity = new MonitorEntity();
		entity.setCreated(new Date(monitorEvent.getCreatedTime()));
		entity.setExecutionTime(monitorEvent.getExecutionTime());
		entity.setFileSize(monitorEvent.getFileSize());
		return save(entity);
	}

	public int save(MonitorEntity monitorEntity) {
		/*
		 * creation_year varchar(20), creation_month varchar(20), creation_year
		 * varchar(20)
		 */

		SimpleDateFormat yearFormatPattern = new SimpleDateFormat(YEAR_FORMAT);
		String yearFormat = yearFormatPattern
				.format(monitorEntity.getCreated());
		SimpleDateFormat monthFormatPattern = new SimpleDateFormat(MONTH_FORMAT);
		String monthFormat = monthFormatPattern.format(monitorEntity
				.getCreated());
		SimpleDateFormat dayFormatPattern = new SimpleDateFormat(DAY_FORMAT);
		String dayFormat = dayFormatPattern.format(monitorEntity.getCreated());
		SimpleDateFormat hourFormatPattern = new SimpleDateFormat(
				HOUR_FORMAT);
		String hourFormat = hourFormatPattern
				.format(monitorEntity.getCreated());

		String sql = " insert into monitor_results(response_time, file_size, created, creation_year,creation_month,creation_date, creation_hour) values(?, ?, ?, ?, ?, ?, ?)";
		Object args[] = { monitorEntity.getExecutionTime(),
				monitorEntity.getFileSize(), monitorEntity.getCreated(),
				yearFormat, monthFormat, dayFormat, hourFormat };

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		int rows = jdbcTemplate.update(sql, args);
		stopWatch.stop();

		if (stopWatch.getTime() > 500L) {
			logger.warn("The data insertion query '{}' took over {} msecs",
					sql, stopWatch.getTime());
		}

		return rows;
	}



	private List<MonitorStatistics> queryStatistics(String sql, Object[] args) {
		return (List<MonitorStatistics>) query(sql, createStatisticRowMapper(), args);
	}
	
	private <T> List<T> query(String sql, RowMapper<T> rowMapper, Object args[]) {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		List<T> result = (List<T>) jdbcTemplate.query(sql, args, rowMapper);
		stopWatch.stop();

		if (stopWatch.getTime() > 500L) {
			logger.warn("The statistics query '{}' took over {} msecs", sql,
					stopWatch.getTime());
		}
		
		return result;  
	}
	

	private RowMapper<MonitorStatistics> createStatisticRowMapper() {
		RowMapper<MonitorStatistics> statisticRowMapper = new RowMapper<MonitorStatistics>() {
			@Override
			public MonitorStatistics mapRow(ResultSet rs, int rowNum)
					throws SQLException {				

				Long maximum = rs.getLong("max");
				Double average = rs.getDouble("average");
				Long minimum = rs.getLong("min");
				Long count = rs.getLong("count");
				String field = rs.getString("field");
				MonitorStatistics statistics = new MonitorStatistics(field , minimum, average, maximum, count); 
				
				return statistics;
			}
		};
		return statisticRowMapper;
	}

	public List<MonitorStatistics> getMonthlyStatistics(Date date, int pageNum, int pageSize) {

		String sql = "select creation_month as field,min(response_time) as min,avg(response_time) as average,  max(response_time) as max, count(id) as count from monitor_results "
				+ "where creation_month = ? group by creation_month order by creation_month desc limit ?,?";
		SimpleDateFormat sdf = new SimpleDateFormat(MONTH_FORMAT);
		String creationDate = sdf.format(date);
		Object args[] = {creationDate, pageNum*pageSize, pageSize};  
		return queryStatistics(sql, args);
	}

	public List<MonitorStatistics> getDailyStatistics(Date date, int pageNum, int pageSize) {

		String sql = "select creation_hour as field,min(response_time) as min,avg(response_time) as average,  max(response_time) as max, count(id) as count from monitor_results "
				+ "where creation_date = ? group by creation_hour order by creation_date desc limit ?,?";
		SimpleDateFormat sdf = new SimpleDateFormat(DAY_FORMAT);
		String creationDate = sdf.format(date);
		Object args[] = {creationDate, pageNum*pageSize, pageSize};  
		
		return queryStatistics(sql, args);
	}
	
	public List<MonitorStatistics> getHourlyStatistics(Date date, int pageNum, int pageSize) {

		String sql = "select creation_hour  as field,min(response_time) as min,avg(response_time) as average,  max(response_time) as max, count(id) as count from monitor_results "
				+ "where creation_hour = ? group by creation_hour  order by creation_hour desc limit ?,?";
		SimpleDateFormat sdf = new SimpleDateFormat(HOUR_FORMAT);
		String creationDate = sdf.format(date);
		Object args[] = {creationDate, pageNum*pageSize, pageSize};  
		return queryStatistics(sql, args);
	}


}
