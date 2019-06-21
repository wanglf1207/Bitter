package com.bitter.esb.utils;


/**
 * 作为ESB服务方时的错误码和错误信息
 * 错误码结构要求如下	4位系统简称 + 1位错误类型 + 5位顺序号
 * 4位系统简称：ILS0
 * 错误类型：0、系统错误，1、应用错误		(所有接口均使用应用错误)
 * 顺序号规划如下：
 * @author wanglf
 *
 */
public class ESBReturnCodeConstants {
	
	public static final String RET_CODE_SUCCESS = "000000";
	public static final String RET_MSG_SUCCESS = "交易成功";
	public static final String RET_CODE_NO_SERVICE = "ILS0000001";
	public static final String RET_MSG_NO_SERVICE = "暂无服务实现，请联系开发人员";
	public static final String RET_CODE_INVOKE_FAILED = "ILS0000002";
	public static final String RET_MSG_INVOKE_FAILED = "服务调用失败，请联系开发人员";
	
	public static final String RET_CODE_TIMEOUT = "ILS0000002";
	public static final String RET_MSG_TIMEOUT = "调用ESB服务超时";
	
	// 提前还款预约
	public static final String RET_CODE_LOANFULLYPAY_FAILED = "ILS0110001";
	public static final String RET_CODE_LOANFULLYPAY_REPEAT_ERROR = "ILS0110002";
	
	// 放款确认
	public final static String RET_CODE_LOANID_NULL_ERROR = "ILS0110003";
	public final static String RET_MSG_LOANID_NULL_ERROR = "贷款编号不能为空！";
	
	// 查询授信案件记录
	public final static String RET_CODE_CASE_NODATA_ERROR = "ILS0110004";
	public final static String RET_MSG_CASE_NODATA_ERROR = "无相关数据！";

	//P2P入参错误码
	public final static String P2P_NULL_INPUT_ERROR_CODE = "ILS0110005";
	public final static String P2P_NULL_INPUT_ERROR_MESSAGE = "必输项不能为空!";
	public final static String P2P_JSON_RESPONSE_ERROR_CODE = "ILS0110006";
	public final static String P2P_JSON_RESPONSE_ERROR_MESSAGE = "返回结果JSON解析异常！";
	public final static String P2P_RESPONSE_ERROR_CODE = "ILS0110007";
	
	//opr入参错误码
	public final static String OPERATE_ERRO_CODE = "ILS0130000";
	public final static String OPERATE_ERRO_MSG = "数据异常";
	public final static String OPERATE_ORDER_NUMBER_REPEAT_CODE = "ILS0130001";
	public final static String OPERATE_ORDER_NUMBER_REPEAT_MSG = "不符合受理条件(订单号重复)";
	
	public final static String OPERATE_SYSTEM_REPEAT_CODE = "ILS0130004";
	public final static String OPERATE_SYSTEM_REPEAT_MSG = "系统异常";
	
	public final static String OPERATE_QUERY_NULL_CODE = "ILS0130002";
	public final static String OPERATE_QUERY_NULL_MSG = "无此交易数据";
	
	
	public final static String SMY_LOAN_PAY_STATUS_CODE = "ILS0130003";
	public final static String SMY_LOAN_PAY_STATUS_MSG = "正在处理中";
	
}
