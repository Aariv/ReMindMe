/**
 * 
 */
package com.ariv.remind.resource;

/**
 * @author al
 *
 */
public class ResponseData {

	private Boolean result;

	private Object data;

	private String message;

	/**
	 * 
	 */
	public ResponseData() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param result
	 * @param data
	 * @param message
	 */
	public ResponseData(Boolean result, Object data, String message) {
		super();
		this.result = result;
		this.data = data;
		this.message = message;
	}

	/**
	 * @return the result
	 */
	public Boolean getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(Boolean result) {
		this.result = result;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
