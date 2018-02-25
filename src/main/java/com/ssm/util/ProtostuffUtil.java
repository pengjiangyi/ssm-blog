package com.ssm.util;

import java.util.ArrayList;
import java.util.List;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

public class ProtostuffUtil {

	public static List<byte[]> serializeProtoStuffObjectList(List list, Class clazz) {
		if (list == null || list.size() <= 0) {
			return null;
		}

		long start = System.currentTimeMillis();
		List<byte[]> bytes = new ArrayList<byte[]>();
		Schema schema = RuntimeSchema.getSchema(clazz);
		LinkedBuffer buffer = LinkedBuffer.allocate(4096);
		byte[] protostuff = null;
		for (Object p : list) {
			try {
				protostuff = ProtostuffIOUtil.toByteArray(p, schema, buffer);
				bytes.add(protostuff);
			} finally {
				buffer.clear();
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("usetime is" + (end - start));
		return bytes;
	}

	public static List deserializeProtoStuffDataListToObjectList(List<byte[]> bytesList, Class clazz) {
		if (bytesList == null || bytesList.size() <= 0) {
			return null;
		}
		long start = System.currentTimeMillis();
		Schema schema = RuntimeSchema.getSchema(clazz);
		List<Object> list = new ArrayList<Object>();
		for (byte[] bs : bytesList) {
			Object obj = null;
			try {
				obj = clazz.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ProtostuffIOUtil.mergeFrom(bs, obj, schema);
			list.add(obj);
		}
		long end = System.currentTimeMillis();
		System.out.println("usetime is" + (end - start));
		return list;
	}

	public static byte[] serializeProtoStuffObject(Object obj, Class clazz) {
		if (obj == null) {
			return null;
		}
		long start = System.currentTimeMillis();
		Schema schema = RuntimeSchema.getSchema(clazz);
		LinkedBuffer buffer = LinkedBuffer.allocate(4096);
		byte[] protostuff = null;
		protostuff = ProtostuffIOUtil.toByteArray(obj, schema, buffer);
		long end = System.currentTimeMillis();
		System.out.println("usetime is" + (end - start));
		return protostuff;
	}

	public static Object deserializeProtoStuffDataListToProductsObject(byte[] bytes, Class clazz) {
		if (bytes == null) {
			return null;
		}
		long start = System.currentTimeMillis();
		Schema schema = RuntimeSchema.getSchema(clazz);

		Object obj = null;
		try {
			obj = clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ProtostuffIOUtil.mergeFrom(bytes, obj, schema);
		long end = System.currentTimeMillis();
		System.out.println("usetime is" + (end - start));
		return obj;
	}

}