package it.al.ma.dao;

import java.sql.Date;
import java.util.List;

import it.al.ma.model.DailyTime;
import it.al.ma.model.User;

public interface DailyTimeDao {
	
	void insertDaily(DailyTime daily);

	   void updateDaily(DailyTime daily);

	   void deleteDaily(DailyTime daily);

	   List<DailyTime> findByIdUser(User user, Date start, Date end);
	   

	

}
