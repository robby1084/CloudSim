package org.iti.rmi.client;

import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.iti.rmi.service.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceConsumer.class);

	// 用于等待SysConnected事件触发后继续执行当前线程
	private CountDownLatch latch = new CountDownLatch(1);
	
	// 定义一个volatile成员变量，用于保存最新的RMI地址（考虑到该变量或许会被其他线程所修改，一旦修改后，该变量的值会影响到所有线程）
	private volatile List<String> urlList = new ArrayList<String>();
	
	// 构造器
	public ServiceConsumer(){
		// 连接ZooKeeper服务器并获取ZooKeeper对象
		ZooKeeper zk = connectServer();
		if (zk != null) {
			// 观察/registry节点的所有子节点并更新urlList成员变量
			watchNode(zk);
		}
	}
	
	// 查找RMI服务
	public <T extends Remote> T lookup(){
		
		T service = null;
		int size = urlList.size();
		if (size > 0) {
			String url;
			if (size == 1) {
				// 若urlList中只有一个元素，则直接获取该元素
				url = urlList.get(0);
				LOGGER.debug("using only url: {}", url);
			}else{
				// 若urlList中存在多个元素，则随机获取一个元素
				url = urlList.get(ThreadLocalRandom.current().nextInt(size));
				LOGGER.debug("using random url: {}", url);
			}
			// 从JNDI中查找RMI服务
			service = lookupService(url);
		}
		return service;
	}
	
	// 连接ZooKeeper服务器
	private ZooKeeper connectServer() {
		ZooKeeper zk = null;
		try {
			zk = new ZooKeeper(Constant.ZK_CONNECT_STRING,
					Constant.ZK_SESSION_TIMEOUT, new Watcher() {

						@Override
						public void process(WatchedEvent event) {

							if (event.getState() == Event.KeeperState.SyncConnected) {
								latch.countDown();
							}
						}
					});
			latch.await();
		} catch (Exception e) {
			LOGGER.error("", e);
		}
		return zk;
	}
	
	// 观察/registry节点下所有子节点是否有变化
	private void watchNode(final ZooKeeper zk){
		try {
			List<String> nodeList = zk.getChildren(Constant.ZK_REGISTRY_PATH, new Watcher(){

				@Override
				public void process(WatchedEvent event) {

					if (event.getType() == Event.EventType.NodeChildrenChanged) {
						// 若子节点有变化，则重新调用该方法，目的是为了获取最新子节点中的数据
						watchNode(zk);
					}
				}});
			// 用于存放/registry所有子节点中的
			List<String> dataList = new ArrayList<String>();
			for(String node : nodeList){
				// 获取/registry的子节点中的数据
				byte[] data = zk.getData(Constant.ZK_REGISTRY_PATH + "/" + node, false, null);
				dataList.add(new String(data));
			}
			LOGGER.debug("node data : {}", dataList);
			// 更新注册RMI地址
			urlList = dataList;
		} catch (Exception e) {
			LOGGER.error("", e);
		}
	}
	
	// 在JNDI中查找RMI远程服务对象
	private <T> T lookupService(String url){
		
		T remote = null;
		try {
			remote = (T) Naming.lookup(url);
		} catch (Exception e) {
			if (e instanceof ConnectException) {
				// 若连接中断，则使用urlList中第一个RMI地址来查找，这是一种简单的重试方式，确保不会出问题
				LOGGER.error("ConnectException -> : {}", url);
				if (urlList.size() != 0) {
					url = urlList.get(0);
					return lookupService(url);
				}
			}
			LOGGER.error("", e);
		}
		return remote;
	}
	
}
