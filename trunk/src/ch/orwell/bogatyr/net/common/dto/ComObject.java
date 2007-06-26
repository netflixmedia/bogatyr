package ch.orwell.bogatyr.net.common.dto;

import java.io.Serializable;

import ch.orwell.bogatyr.Context;
import ch.orwell.bogatyr.util.Localizer;
import ch.orwell.bogatyr.util.Logger;


/**
 * This is the communication object for the client/server-communication.
 * 
 * @author Roman Wuersch
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070623
 */
public final class ComObject implements Serializable {
	private static final long serialVersionUID = -737966508835228621L;
	private String className;
	private long createTime = System.currentTimeMillis();
	
	private String userKey;
	private String methodName;
	private Object data;
	private Exception exception;

	
	/**
	 * Constructs a ComObject.
	 * @param userKey The user key for authentication
	 * @param methodName The method name that will be invoked
	 * @param data The Dataobject to send with
	 */
	public ComObject(String userKey, String methodName, Object data) {
		this.userKey = userKey;
		this.methodName = methodName;
		this.data = data;

		init();
	}
	
	/**
	 * Constructs a ComObject.
	 * @param userKey The user key for authentication
	 * @param data The Dataobject to send with
	 */
	public ComObject(String userKey, Object data) {
		this.userKey = userKey;
		this.data = data;

		init();
	}
	
	public Object getData() {
		return this.data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public Exception getException() {
		return this.exception;
	}
	
	public void setException(Exception exception) {
		this.exception = exception;
	}
	
	public String getMethodName() {
		return this.methodName;
	}
	
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getUserKey() {
		return this.userKey;
	}
	
	
	/*
	 * Private methods
	 */
	/**
	 * Initialize the object<p>
	 * Do some logging.
	 */
	private void init() {
		this.className = this.getClass().getName();
		Logger.getInstance().writeDebug(this.className + "::init", Context.getInstance().getLocalizer().getValue(Localizer.RES_INSTANCIATED) + toString()); //$NON-NLS-1$
	}

	
	/*
	 * Overriden methods
	 */
	@Override
	public String toString() {
		return "\nclassName: " + this.className + //$NON-NLS-1$
			"\ncreateTime: " + this.createTime + //$NON-NLS-1$
			"\nuserKey: " + this.userKey + //$NON-NLS-1$
			"\nmethodName: " + this.methodName + //$NON-NLS-1$
			"\ndata: " + this.data; //$NON-NLS-1$
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((this.className == null) ? 0 : this.className.hashCode());
		result = PRIME * result + (int) (this.createTime ^ (this.createTime >>> 32));
		result = PRIME * result + ((this.data == null) ? 0 : this.data.hashCode());
		result = PRIME * result + ((this.methodName == null) ? 0 : this.methodName.hashCode());
		result = PRIME * result + ((this.userKey == null) ? 0 : this.userKey.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ComObject other = (ComObject) obj;
		if (this.className == null) {
			if (other.className != null)
				return false;
		} else if (!this.className.equals(other.className))
			return false;
		if (this.createTime != other.createTime)
			return false;
		if (this.data == null) {
			if (other.data != null)
				return false;
		} else if (!this.data.equals(other.data))
			return false;
		if (this.methodName == null) {
			if (other.methodName != null)
				return false;
		} else if (!this.methodName.equals(other.methodName))
			return false;
		if (this.userKey == null) {
			if (other.userKey != null)
				return false;
		} else if (!this.userKey.equals(other.userKey))
			return false;
		return true;
	}
}
