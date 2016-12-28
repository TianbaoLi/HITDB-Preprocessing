package com.company;

import jdk.internal.org.objectweb.asm.Type;

import javax.swing.*;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by TuringMac on 2016/12/28.
 */
public class SqlReader extends Reader {
    private String ip;
    private int port;
    private String database;
    private String table;
    private String user;
    private String password;
    private String[] schema;
    private String[] type;
    private String dbUrl = "jdbc:mysql://";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public SqlReader(File file, JTable displayTable, String ip, int port, String database, String table, String user, String password) {
        super(file, displayTable);
        this.ip = ip;
        this.port = port;
        this.database = database;
        this.table = table;
        this.user = user;
        this.password = password;
        this.schema = null;
        this.type = null;
        dbUrl += ip + ':' + String.valueOf(port) + "/" + database;
    }

    public void read() throws Exception {
        Connection connection = null;
        Statement statement = null;
        try{
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(dbUrl, user, password);
            statement = connection.createStatement();
            String sql = "SELECT * FROM " + table;
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            ArrayList<String> schemaList = new ArrayList<>();
            ArrayList<String> typeList = new ArrayList<>();
            String attribute;
            String tp;
            for(int i = 1; i <= columnCount; i++){
                attribute = resultSetMetaData.getColumnName(i);
                tp = resultSetMetaData.getColumnTypeName(i);
                schemaList.add(attribute);
                typeList.add(tp);
            }
            schema = schemaList.toArray(new String[0]);
            type = typeList.toArray(new String[0]);
            for(String s : schemaList)
                System.out.print(s + "\t");
            System.out.println();
            for(String t : type)
                System.out.print(t + "\t");
            System.out.println();
            ArrayList<Object> attrList;
            Object attr = null;
            while(resultSet.next()){
                attrList = new ArrayList<>();
                for(int i = 1; i <= type.length; i++){
                    switch(type[i - 1]){
                        case "BIGINT":
                            attr = resultSet.getLong(i);
                            break;
                        case "BIT":
                            attr = resultSet.getByte(i);
                            break;
                        case "BOOLEAN":
                            attr = resultSet.getBoolean(i);
                            break;
                        case "CHAR":
                            attr = resultSet.getString(i);
                            break;
                        case "DATE":
                            attr = resultSet.getDate(i);
                            break;
                        case "DOUBLE":
                            attr = resultSet.getDouble(i);
                            break;
                        case "FLOAT":
                            attr = resultSet.getFloat(i);
                            break;
                        case "INTEGER":
                            attr = resultSet.getInt(i);
                            break;
                        case "SMALLINT":
                            attr = resultSet.getInt(i);
                            break;
                        case "TIME":
                            attr = resultSet.getTime(i);
                            break;
                        case "TINYINT":
                            attr = resultSet.getShort(i);
                            break;
                        case "VARCHAR":
                            attr = resultSet.getString(i);
                            break;
                        case "NCHAR":
                            attr = resultSet.getNString(i);
                            break;
                        case "NVARCHAR":
                            attr = resultSet.getNString(i);
                            break;
                    }
                    attrList.add(attr);
                }
                for(Object o : attrList)
                    System.out.print(o + "\t");
                System.out.println();
            }
            resultSet.close();
            statement.close();
            connection.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(statement != null) statement.close();
            }catch(SQLException se2){
                se2.printStackTrace();
            }
            try{
                if(connection != null) connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}
