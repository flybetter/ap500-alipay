package org.alipay.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class AlipayConfig {

	@Value("${slat}")
	public  String slat;
	
	@Value("${order_invaid}")
	public Integer  order_invaid;
	
	public static String COMPANYACCOUNT="4000606246@autophone.com.cn";
	
	public static String BUYERTYPE="个人";
	
	public static String PAYCHANNEL="支付宝";
	
	public static String PAYMETHOD="扫码支付";
	
	public static String PAYTYPE="终端方式";
	
	public static String SUCCESS="已付款";
	
	public static String FAIL="未付款";
	
	public static String ORDER="已下单";
	
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

		// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
		public static String partner = "2088912429633964";
		
		// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
		public static String seller_id = partner;

		// MD5密钥，安全检验码，由数字和字母组成的32位字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	    public static String key = "ydak9erdzlcie78kpl3s6f716qex9y6m";
	    
	 // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	    @Value("${notify_url}")
	 	public  String notify_url;

	 	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	    @Value("${return_url}")
	 	public  String return_url;

		// 签名方式
		public static String sign_type = "MD5";
		
		// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
		public static String log_path = "D:\\";
			
		// 字符编码格式 目前支持 gbk 或 utf-8
		public static String input_charset = "utf-8";
			
		// 支付类型 ，无需修改
		public static String payment_type = "1";
			
		// 调用的接口名，无需修改
		public static String service = "create_direct_pay_by_user";

		
		
		
	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
		
	//↓↓↓↓↓↓↓↓↓↓ 请在这里配置防钓鱼信息，如果没开通防钓鱼功能，为空即可 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

		// 防钓鱼时间戳  若要使用请调用类文件submit中的query_timestamp函数
		public static String anti_phishing_key = "";
		
		// 客户端的IP地址 非局域网的外网IP地址，如：221.0.0.1
		public static String exter_invoke_ip = "";
			
	//↑↑↑↑↑↑↑↑↑↑请在这里配置防钓鱼信息，如果没开通防钓鱼功能，为空即可 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
		


}
