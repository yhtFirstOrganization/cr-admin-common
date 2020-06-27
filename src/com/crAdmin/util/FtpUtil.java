package com.crAdmin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;

import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

/** * Java自带的API对FTP的操作 * @Title:Ftp.java * @author: wdd */
@SuppressWarnings("restriction")
public class FtpUtil {
	private String localfilename;
	private String remotefilename;
	private FtpClient ftpClient;

	public void connectServer(String ip, String port, String user,
			String password, String path) {
		try {
			/* ******连接服务器的两种方法****** */
			ftpClient = FtpClient.create();
			try {
				int iport = Integer.parseInt(port);
				SocketAddress addr = new InetSocketAddress(ip, iport);
				ftpClient.connect(addr);
				ftpClient.login(user, password.toCharArray());
				System.out.println("login success!");
				if (path.length() != 0) {
					// 把远程系统上的目录切换到参数path所指定的目录
					ftpClient.changeDirectory(path);
				}
			} catch (FtpProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	/** * 关闭连接 * @author wdd * @date 2014-9-1 */
	public void closeConnect() {
		try {
			ftpClient.close();
			System.out.println("disconnect success");
		} catch (IOException ex) {
			System.out.println("not disconnect");
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	/**
	 * * 上传文件 * @param localFile 本地文件 * @param remoteFile 远程文件 * @author wdd * @date
	 * 2014-9-1
	 */
	public void upload(String localFile, String remoteFile) {
		this.localfilename = localFile;
		this.remotefilename = remoteFile;
		OutputStream os = null;
		FileInputStream is = null;

		try {
			// 将远程文件加入输出流中
			try {
				os = ftpClient.putFileStream(this.remotefilename);
			} catch (FtpProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 获取本地文件的输入流
			File file_in = new File(this.localfilename);
			is = new FileInputStream(file_in);
			// 创建一个缓冲区
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				os.write(bytes, 0, c);
			}

			System.out.println("upload success");

		} catch (IOException ex) {
			System.out.println("not upload");
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (os != null) {
						os.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * /** * 下载文件 * @param remoteFile 远程文件路径(服务器端) * @param localFile
	 * 本地文件路径(客户端) * @author wdd * @date 2014-9-1
	 */
	public void download(String remoteFile, String localFile) {
		InputStream is = null;
		FileOutputStream os = null;
		try {
			// 获取远程机器上的文件filename，借助TelnetInputStream把该文件传送到本地。
			try {
				is = ftpClient.getFileStream(remoteFile);
			} catch (FtpProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			File file_in = new File(localFile);
			os = new FileOutputStream(file_in);
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				os.write(bytes, 0, c);
			}

			System.out.println("download success");

		} catch (IOException ex) {
			System.out.println("not download");
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (os != null) {
						os.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String agrs[]) {
		// String filepath[] = { "/1.ai", "/scheme.xml"};
		// String localfilepath[] = {
		// "/home/wddpeakking/soft/1.ai","/home/wddpeakking/soft/wdd.xml"};
		FtpUtil fu = new FtpUtil();
		/* * 使用默认的端口号、用户名、密码以及根目录连接FTP服务器 */
		fu.connectServer(ResourceUtil.getFtpIp(), ResourceUtil.getFtpPort(),
				ResourceUtil.getFtpUserName(), ResourceUtil.getFtpPassword(),
				"");
		// 下载
		// for (int i = 0; i < filepath.length; i++) {
		// fu.download(filepath[i], localfilepath[i]); }
		String localfile = "/E:tet.png";
		String remotefile = "/qq/aa.pn";
		// 上传
		SimpleDateFormat sDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd   hh:mm:ss");
		System.out.println(sDateFormat.format(new java.util.Date()));
		// new Date()为获取当前系统时间
		fu.upload(localfile, remotefile);
		System.out.println(sDateFormat.format(new java.util.Date()));// new
																		// Date()为获取当前系统时间
		fu.closeConnect();
	}

}