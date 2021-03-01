package org.mac.fds.namenode;

import lombok.Getter;
import lombok.Setter;

/**
 * 用来描述datanode的信息
 * @author machi
 *
 */
@Getter
@Setter
public class DataNodeInfo {

	private String ip;
	private String hostname;
	private long latestHeartbeatTime = System.currentTimeMillis();
  	
	public DataNodeInfo(String ip, String hostname) {
		this.ip = ip;
		this.hostname = hostname;
	}
}
