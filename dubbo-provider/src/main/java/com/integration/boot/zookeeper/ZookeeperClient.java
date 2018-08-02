package com.integration.boot.zookeeper;

import java.io.IOException;
import java.util.List;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.data.Stat;

public class ZookeeperClient {
	private static final String PATH = "/data";

	public static void main(String[] args) throws IOException {
		CuratorFramework client = null;
		PathChildrenCache cache = null;
		try {
			//使用CuratorFrameworkFactory建立客户端到zookeeper服务器端的连接并启动
			//client = CuratorFrameworkFactory.newClient("192.168.48.131:2181", new
			//RetryUntilElapsed(3000, 1000));
			client = createWithOptions("192.168.48.131:2181", new ExponentialBackoffRetry(10000, 3), 10000, 10000);
			client.start();
			cache = new PathChildrenCache(client, PATH, true);
			cache.getListenable().addListener(new Listener());
			cache.start(true);//如果设置为true,那么NodeCache在第一次启动打的时候就会立刻在Zookeeper上读取对应节点的数据内容，并保存在Cache中
			String data = "4754";
			Stat stat = client.checkExists().forPath("/data/childOne");
			if(stat == null) {
				client.create().forPath("/data/childOne", new byte[0]);
			}
			client.getData().forPath("/data/childOne");
			client.setData().forPath("/data/childOne", data.getBytes());
			client.getChildren().forPath("/data/childOne");
			List<String> forPath = client.getChildren().forPath("/data");
			if(forPath != null && forPath.size()>0) {
				for(String path:forPath) {
					try {
						client.delete().forPath("/data/"+path);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
           e.printStackTrace();
		}
		System.in.read();
	}

	public static CuratorFramework createWithOptions(String connectionString, RetryPolicy retryPolicy,
			int connectionTimeoutMs, int sessionTimeoutMs) {

		return CuratorFrameworkFactory.builder().connectString(connectionString).retryPolicy(retryPolicy)
				.connectionTimeoutMs(connectionTimeoutMs).sessionTimeoutMs(sessionTimeoutMs).build();
	}

	private static class Listener implements PathChildrenCacheListener {

		@Override
		public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
			switch (event.getType()) {
			case CHILD_ADDED: {
				System.out.println(client.getData());
				System.out.println("Node added: " + ZKPaths.getNodeFromPath(event.getData().getPath())+","+new String(event.getData().getData()));
				break;
			}
			case CHILD_UPDATED: {
				System.out.println("Node changed: " + ZKPaths.getNodeFromPath(event.getData().getPath())+","+new String(event.getData().getData()));
				break;
			}
			case CHILD_REMOVED: {
				System.out.println("Node removed: " + ZKPaths.getNodeFromPath(event.getData().getPath())+","+new String(event.getData().getData()));
				break;
			}
			default:
				System.out.println("no node changed!!!");
				break;
			}
		}

	}

}
