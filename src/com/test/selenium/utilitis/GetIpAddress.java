package com.test.selenium.utilitis;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class GetIpAddress {

	public static void main(String[] args) {
	    try {
			System.out.println("Your Host addr: " + InetAddress.getLocalHost().getHostAddress());
			System.out.println("Your Host addr: " + InetAddress.getLoopbackAddress());
	    } catch (UnknownHostException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}  // often returns "127.0.0.1"
	    System.out.println(getLocalIp());
	}
	public static String getLocalIp(){
		String localIp = "";
		Enumeration<NetworkInterface> n;
		try {
			n = NetworkInterface.getNetworkInterfaces();		
			while(n.hasMoreElements())
		    {
		        NetworkInterface e = n.nextElement();
		    	if (e.isLoopback() || !e.isUp() || e.getDisplayName().contains("Tunnel") || e.getDisplayName().contains("VirtualBox") )
	                continue;
		        Enumeration<InetAddress> a = e.getInetAddresses();
		        while(a.hasMoreElements())
		        {
		            InetAddress addr = a.nextElement();
		            if (addr.getHostAddress().matches("^[0-9]+.[0-9]+.[0-9]+.[0-9]+$")){
		            	localIp = addr.getHostAddress();
			            System.out.println("ip: " + localIp);
		            }
		        }
		    }
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return localIp;
	}
}