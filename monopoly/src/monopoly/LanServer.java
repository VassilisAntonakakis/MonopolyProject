package monopoly;

import java.net.*;

public class LanServer {
	private int port;
	private InetAddress addr;
	
	public LanServer() throws UnknownHostException {
		this.setAddr(InetAddress.getLocalHost());
		System.out.println("Ip: " + this.getAddr());
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public InetAddress getAddr() {
		return addr;
	}

	public void setAddr(InetAddress addr) {
		this.addr = addr;
	}
}
