package org.mac.fds.namenode.server;

/**
 * 用来描述datanode的信息
 * @author machi
 *
 */
public class DataNodeInfo {

	private String ip;

	private String hostname;

	//上次心跳发送的时间
	private long lastHeatBeatTime;

	public long getLastHeatBeatTime() {
		return lastHeatBeatTime;
	}

	public void setLastHeatBeatTime(long lastHeatBeatTime) {
		this.lastHeatBeatTime = lastHeatBeatTime;
	}

	public DataNodeInfo(String ip, String hostname) {
		this.ip = ip;
		this.hostname = hostname;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
}
