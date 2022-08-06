package exercise2_2;

import java.sql.*;
import java.util.Scanner;

public class Connect {
    Scanner sc = new Scanner(System.in);
    private final String url = "jdbc:postgresql://localhost:5432/ekzamen";
    private final String user = "postgres";
    private final String password = "abdutokt2004";

    public Connection connection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insertNews() {
        String sqlInsertNews = "insert into \"ekzamen4\".news (news_headline, text_news) values (?, ?)";
        try {
            PreparedStatement preparedStatement = connection().prepareStatement(sqlInsertNews);
            System.out.println("Введите заголовок новости");
            String headline = sc.nextLine();
            preparedStatement.setString(1, headline);
            System.out.println("Введите текст новости");
            String text = sc.nextLine();
            preparedStatement.setString(2, text);
            preparedStatement.executeUpdate();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    public void selectNews() {
        String sqlSelectNews = "select news_headline, text_news from \"ekzamen4\".news";
        try {
            Statement statement = connection().createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSelectNews);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("news_headLine") + " " + resultSet.getString("text_news"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteNews() {
        String sqlDeleteNews = "delete from \"ekzamen4\".news where id = ?";
        try {
            PreparedStatement preparedStatement = connection().prepareStatement(sqlDeleteNews);
            System.out.println("Введите id новости для удаления");
            int deleteNews = sc.nextInt();
            preparedStatement.setInt(1, deleteNews);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateNews() {
        String sqlUpdateNews = "update \"ekzamen4\".news set news_headline = ?, text_news = ? where id = ?";
        try {
            PreparedStatement preparedStatement = connection().prepareStatement(sqlUpdateNews);
            System.out.println("Введите id новости для изменения");
            int idNews = sc.nextInt();
            preparedStatement.setInt(3, idNews);
            System.out.println("Введите заголовок новости");
            sc.nextLine();
            String updateNewsLine = sc.nextLine();
            preparedStatement.setString(1, updateNewsLine);
            System.out.println("Введите текст новости");
            String updateNewsText = sc.nextLine();
            preparedStatement.setString(2, updateNewsText);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
