package org.alipay.service.impl;



import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.alipay.dao.FeepackageDao;
import org.alipay.dao.MypackageDao;
import org.alipay.entity.Feepackage;
import org.alipay.entity.Mypackage;
import org.alipay.service.MypackageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mypackageService")
public class MypackageServiceImpl implements MypackageService {
	
	Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MypackageDao mypackageDao;
	
	@Autowired 
	private FeepackageDao feepackageDao;

	@Override
	public List<Mypackage> getMypackageListByType(Integer userId,Integer type) {
		List<Mypackage> mypackageList=mypackageDao.queryByUserIdAndPackageType(userId, type);
		return mypackageList;
	}

	@Override
	public Integer insertMypackage(Integer userId, Integer feepackageId) {
		Feepackage feepackage=feepackageDao.queryById(feepackageId);
		Date endTime=new Date();
		Date startTime=new Date();
		
		//当月27日零点零分
		Calendar curr27Calendar = Calendar.getInstance();
		curr27Calendar.set(Calendar.DAY_OF_MONTH, 27);
		curr27Calendar.set(Calendar.HOUR_OF_DAY, 0);
		curr27Calendar.set(Calendar.MINUTE, 0);
		curr27Calendar.set(Calendar.SECOND, 0);
		
		if(feepackage.getChargingWay()=='1'){//当月有效
			endTime=curr27Calendar.getTime();
		}else if(feepackage.getChargingWay()=='0'){//累积型
			List<Mypackage> mypackageList=mypackageDao.queryByUserIdAndPackageType(userId, Integer.parseInt(String.valueOf(feepackage.getType())));
			if(mypackageList!=null&&mypackageList.size()>0){
				Mypackage top=mypackageList.get(mypackageList.size()-1);
				Calendar calendar = Calendar.getInstance(); 
				calendar.setTime(top.getEndTime());
				calendar.add(Calendar.MONTH, feepackage.getLeastPurchase());
				startTime=top.getEndTime();
				endTime=calendar.getTime();
			}else{
				
				//当前日期
				Calendar calendar = Calendar.getInstance();
				if(calendar.before(curr27Calendar)){
					//以上个月27日零点零分为startTime
					curr27Calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-1);
					startTime=curr27Calendar.getTime();
					curr27Calendar.add(Calendar.MONTH, feepackage.getLeastPurchase());
					endTime=curr27Calendar.getTime();
				}else{
					startTime=curr27Calendar.getTime();
					curr27Calendar.add(Calendar.MONTH, feepackage.getLeastPurchase());
					endTime=curr27Calendar.getTime();
				}
			}
		}
		Integer result=mypackageDao.insertMypackage(feepackageId, startTime, endTime, Integer.parseInt(String.valueOf(feepackage.getType())),Integer.parseInt(String.valueOf(feepackage.getChargingWay())), userId);
		return result;
	}

}
