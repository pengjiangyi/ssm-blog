package com.ssm.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import redis.clients.jedis.Jedis;

public class SerializeUtil {
	/*
	 * 序列化
	 */
	public static byte[] serizlize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (baos != null) {
					baos.close();
				}
				if (oos != null) {
					oos.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	/*
	 * 反序列化
	 */
	public static Object deserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try {
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 设置对象
	 * 
	 * @param key
	 * @param obj
	 */
	public static void setObject(String key, Object obj, Jedis jedis) {
		try {
			obj = obj == null ? new Object() : obj;
			jedis.set(key.getBytes(), SerializeUtil.serizlize(obj));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 设置对象
	 * 
	 * @param key
	 * @param obj
	 */
	public static void setObjectAndTime(String key, Object obj, Jedis jedis,int time) {
		try {
			obj = obj == null ? new Object() : obj;
			jedis.setex(key.getBytes(), time,SerializeUtil.serizlize(obj));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	

	/**
	 * 获取对象
	 * 
	 * @param key
	 * @return Object
	 */
	public static Object getObject(String key, Jedis jedis) {
		if (jedis == null || !jedis.exists(key)) {
			return null;
		}
		byte[] data = jedis.get(key.getBytes());
		return (Object) SerializeUtil.deserialize(data);
	}

}