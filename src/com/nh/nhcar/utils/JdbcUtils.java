package com.nh.nhcar.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.ResultSetMetaData;

import com.mysql.jdbc.Statement;


public class JdbcUtils {
	private final String driver = "com.mysql.jdbc.Driver";
	private final String url = "jdbc:mysql://localhost:3306/nhcar_web?useUnicode=true&characterEncoding=utf8";
	private final String dbName = "root";
	private final String dbPwd = "123456";
	private Connection cont;
	private PreparedStatement stmt;

	public JdbcUtils() {}

	/**
	 * ��������
	 * 
	 * @throws Exception
	 */
	private void intConnect(){
		// ������
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("����mysql������");
		}
		// �������Ӳ���
		try {
			cont = DriverManager.getConnection(url, dbName, dbPwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�������ݿ����");
		}
		// �������ִ��
	}

	/**
	 * ��ʼ������
	 * 
	 * @param sql
	 * @param paras
	 * @throws SQLException
	 */
	private void initParas(String sql, List<Object> paras) throws SQLException {
		stmt = cont.prepareStatement(sql);
		if (paras != null && paras.size() > 0) {
			int index = 1;
			for (int i = 0; i < paras.size(); i++) {
				stmt.setObject(index++, paras.get(i));
			}
		}
	}

	/**
	 * ִ��insert/update/delete���޸Ĳ���
	 * 
	 * @param sql
	 * @param paras
	 * @return
	 * @throws Exception
	 */
	public int update(String sql, List<Object> paras) {
		int result=0;
		try {
			intConnect();
			initParas(sql, paras);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			close();
		}
		
		return result;
	}
	public int insertreturnID(String insertsql,List<Object> paras){
		int result=0;
		try{
			intConnect();
			stmt=cont.prepareStatement(insertsql,Statement.RETURN_GENERATED_KEYS);
			initParas(insertsql,paras);
			stmt.executeUpdate();
			ResultSet rs=stmt.getGeneratedKeys();
			rs.next();
			result=rs.getInt(1);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			close();
		}
		return result;
	}

	/**
	 * ���ص��м�¼
	 * 
	 * @param sql
	 * @param paras
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> queryOneRow(String sql, List<Object> paras) {
		Map<String, Object> oneRow = new HashMap<String, Object>();
		try {
			intConnect();
			initParas(sql, paras);
			ResultSet result = stmt.executeQuery();
			List<Map<String, Object>> resultList = convertResultSetToMapList(result);
			if (resultList.size() > 0) {
				oneRow = resultList.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("ִ��queryOneRow��������"+sql+"��");
		}
		finally{
			close();
		}
		return oneRow;
	}

	/**
	 * ���ض��м�¼
	 * 
	 * @param sql
	 * @param paras
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> query(String sql, List<Object> paras){
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		try {
			intConnect();
			initParas(sql, paras);
			ResultSet result = stmt.executeQuery();
			resultList = convertResultSetToMapList(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ִ��query��������"+sql+"��");
		}
		finally{
			close();
		}
		
		return resultList;
	}
	/**
	 * ���ض�����¼�����������Ĳ�ѯ
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> query(String sql) {
		return query(sql,null);
	}
	/**
	 * ���ص�������ֵ
	 * @param sql
	 * @param paras
	 * @return
	 */
	public Object queryOne(String sql, List<Object> paras){
		Object result = null;
		
		try {
			intConnect();
			initParas(sql, paras);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				result = rs.getObject(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ִ��queryOne��������"+sql+"��");
		}
		finally{
			close();
		}
		return result;
	}
	/**
	 * ���ݲ�ѯ��ȡ��ҳ����Լ���ҳ����Ҫ����Ϣ
	 * 
	 * @param cmdtext
	 * @param params
	 * @param page����ǰҳ��
	 * @param pageSize:��ҳ��ʾ��¼��
	 * @return PageSet��ҳ�����
	 * @throws Exception
	 */
	public PageSet queryPage(String sql,List<Object> paras, int pno, int pageSize){
		PageSet pageSet = new PageSet();
		String selectCountSQL = "select count(1) as total from (" + sql + ") c_t";
		int pageCount = Integer.parseInt(queryOne(selectCountSQL, paras).toString());
		pageSet.setPage(pno);
		pageSet.setPageSize(pageSize);
		pageSet.setDataCount(pageCount);
		String selectPageSQL = sql + " limit " + pageSet.getStart() + ","
				+ pageSize;
		pageSet.setDataResult(query(selectPageSQL, paras));
		return pageSet;

	}
	/**
	 * ��ResultSetת����List�б�
	 * @param result
	 * @return
	 * @throws SQLException
	 */
	private List<Map<String, Object>> convertResultSetToMapList(ResultSet result)
			throws SQLException {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		ResultSetMetaData metaData = result.getMetaData();
		int colnum = metaData.getColumnCount();
		while (result.next()) {
			Map<String, Object> oneRow = new HashMap<String, Object>();
			for (int i = 0; i < colnum; i++) {
				String cName = metaData.getColumnName(i + 1);
				Object cValue = result.getObject(cName);
				if (cValue == null) {
					cValue = "";
				}
				oneRow.put(cName, cValue);
			}
			resultList.add(oneRow);
		}
		return resultList;
	}

	/**
	 * �ر����ݿ�
	 * 
	 * @throws SQLException
	 */
	public void close(){
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (cont != null && !cont.isClosed()) {
				cont.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public static void main(String[] args) {
		
	}
}
