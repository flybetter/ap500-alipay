package org.alipay.dao.cache;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class RedisDao {

	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	private final JedisCluster jedisCluster;
	
	public RedisDao(String hostlink){
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		String[] hosts = hostlink.split(",");
   	 	for(String host : hosts){
   	 		int pos = host.indexOf(":");
   	 		if(pos > 0){
   	 			String ip = host.substring(0,pos);
   	 			try{
   	 				int port = Integer.parseInt(host.substring(pos+1));
   	 			   jedisClusterNodes.add(new HostAndPort(ip,port));
   	 			}catch( NumberFormatException e){
   	 				;//XXX do nothing for NumberFormatException
   	 			}
   	 		}
   	 	}
	     jedisCluster = new JedisCluster(jedisClusterNodes);
	};
	
	
	public String getRedisObject(String key){
		try {
			String value=jedisCluster.get(key);
			return value;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
	}
		return null;
	}
	

	public String putRedisObject(String key,String value){
			try {
				int timeout=60*60*24;//24个小时
				String result=jedisCluster.setex(key, timeout, value);
				return result;
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
			return null;	
	}
	
}
