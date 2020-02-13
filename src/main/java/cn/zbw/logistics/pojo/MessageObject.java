package cn.zbw.logistics.pojo;

import cn.zbw.logistics.service.UserService;

public class MessageObject {
	private Integer code;
	private String msg;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public MessageObject(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	public MessageObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MessageObject [code=" + code + ", msg=" + msg + "]";
	}
	
}
