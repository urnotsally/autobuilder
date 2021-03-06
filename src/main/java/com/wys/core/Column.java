package com.wys.core;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库列实体
 * 
 * @author Yinglong Xu
 * @date 2012-2-16
 */
public class Column {
	/**
	 * 列数据类型
	 */
	private String type;
	/**
	 * 字段名
	 */
	private String name;
	/**
	 * 字段名：java风格，驼峰式
	 */
	private String nameJ;
	
	/**
	 * 字段备注、注释
	 */
	private String remark;
	
	private String jdbcType;

    /**
     * PRI-主键约束，UNI-唯一约束，MUL-索引，可重复
     */
	private String index;

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	/**
	 * 默认构造
	 */
	public Column() {
	}

	/**
	 * 构造
	 * @param type 
	 * @param nameJ
	 */
	public Column(String type, String nameJ) {
		this.type = type;
		this.nameJ = nameJ;
	}
	
	/**
	 * 全构造
	 * @param type
	 * @param name
	 * @param nameJ
	 * @param remark
	 */
	public Column(String name,String jdbcType, String index,String remark, String nameJ, String type ) {
		this.type = type;
		this.name = name;
		this.nameJ = nameJ;
		this.remark = remark;
		this.jdbcType = jdbcType;
		this.index=index;
	}

	/**
	 * 获取Type
	 * @return String
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置Type
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取列名
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置列名
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取javastyle列
	 * @return String
	 */
	public String getNameJ() {
		return nameJ;
	}

	/**
	 * 设置javastyle列
	 * @param nameJ
	 */
	public void setNameJ(String nameJ) {
		this.nameJ = nameJ;
	}

	/**
	 * 获取备注
	 * @return String
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * 列里是否包含某个类型了
	 * @author xuyl
	 * @date 2013-1-8
	 * @param columns
	 * @param type
	 * @return
	 */
	public static boolean typeContains(List<Column> columns, String type) {
		for (Column c : columns) {
			if (c.type.equals(type)) return true;
		}
		return false;
	}

    /**
     * 返回主键列表
     * @param columns
     */
    public static List<Column> getPrimaryKey(List<Column> columns) {
        List<Column> primaryKeys = new ArrayList<>();
        for(Column c : columns) {
            if(c.index.contains("PRI")){
                primaryKeys.add(c);
            }
        }
        return  primaryKeys;
    }

	/**
	 * 返回唯一索引
	 * @param columns
	 */
	public static List<Column> getUniqueIndex(List<Column> columns) {
		List<Column> primaryKeys = new ArrayList<>();
		for(Column c : columns) {
			if(c.index.contains("UNI")){
				primaryKeys.add(c);
			}
		}
		return  primaryKeys;
	}

	@Override
	public String toString() {
		return "Column [type=" + type + ", name=" + name + ", nameJ=" + nameJ + ", remark=" + remark + "]";
	}

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
