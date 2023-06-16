package org.example;

import java.sql.*;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args ) {
        if (args.length != 2) {
            System.out.println(
                    "Application needs two arguments to run: " +
                            "java com.hca.jdbc.UsingDriverManager <username> " +
                            "<password>");
            System.exit(1);
        }
            String username = args[0];
            String password = args[1];

            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try{
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/northwind",
                        username, password);

                preparedStatement = connection.prepareStatement("SELECT contactname, companyname, city, country, phone FROM customers ORDER BY country;");

                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    System.out.printf("Contact Name = %s " + "\n" + "Company Name = %s;" + "\n" + "City = %s" + "\n" + "Country = %s" + "\n" + "Phone = %s" + "\n" + "---------------------",
                    resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
            finally {
                if (resultSet != null){
                    try{
                        resultSet.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (preparedStatement != null){
                    try{
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null){
                    try{
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
    }
}