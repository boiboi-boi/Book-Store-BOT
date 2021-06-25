import java.io.Reader;
import java.sql.*;
import java.util.ArrayList;

public class Login {
    private Connection connection;
    private Statement statement;
    private ResultSet result;

    public Login (){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "387783");
            statement = connection.createStatement();


        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    public String add_new_book(String title, String author, Integer collateral_value, String genre){
        String sql = "call add_new_book(?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, author);
            statement.setInt(3, collateral_value);
            statement.setString(4, genre);
            statement.executeUpdate();
            return "Информация добавлена успешно!";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "Ошибка! Информация не добавлена";
        }

    }

    public String add_new_reader(String last_name, String first_name, String middle_name, String address, String tel_number, Date date) {
        String sql = "call add_new_reader(?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, last_name);
            statement.setString(2, first_name);
            statement.setString(3, middle_name);
            statement.setString(4, address);
            statement.setString(5, tel_number);
            statement.setDate(6, date);

            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "Ошибка! Информация не добавлена";
        }
        return "Выполнено!";
    }

    public String add_new_issue(String book_id, String reader_id, Date date) {
        String sql = "call add_new_issue(?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, book_id);
            statement.setString(2, reader_id);
            statement.setDate(3, date);

            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "Ошибка! Информация не добавлена";
        }
        return "Выполнено!";
    }

    public String search_last_name(String last_name) {
        try {
            String line = "";
            PreparedStatement statement = null;
            String sql = "SELECT * FROM readers where last_name like " + "\'" + last_name + "\'";
            statement = connection.prepareStatement(sql);
            result = statement.executeQuery();
            String discount = "";

            while (result.next()){
                if (result.getBoolean(7)){
                    discount = "Есть скидка";
                } else {
                    discount = "Нет скидки";
                }
                line =  line  + "\uD83D\uDE42 "+result.getString(1)+ "\n"+ "\uD83D\uDC55 "+  result.getString(2)+ "\n"+
                        "\uD83E\uDE73 "+result.getString(3)+ "\n" + "\uD83C\uDFE0 "+result.getString(4) +"\n"+
                        "\uD83D\uDCDE " +result.getString(5)+"\n" +"\uD83D\uDD70 " +result.getDate(6)+"\n" +
                        "\uD83C\uDF81 "+ discount  + "\n\n";

            }
            return line;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "Ошибка!";
        }
    }

    public String search_tel_number(String tel_number) {
        try {
            String line = "";
            PreparedStatement statement = null;
            String sql = "SELECT * FROM readers where tel_number like " + "\'"+tel_number+"\'" ;
            statement = connection.prepareStatement(sql);
            result = statement.executeQuery();
            String discount = "";

            while (result.next()) {
                if (result.getBoolean(7)) {
                    discount = "Есть скидка";
                } else {
                    discount = "Нет скидки";
                }
                line = line + "\uD83D\uDE42 " + result.getString(1) + "\n" + "\uD83D\uDC55 " + result.getString(2) + "\n" +
                        "\uD83E\uDE73 " + result.getString(3) + "\n" + "\uD83C\uDFE0 " + result.getString(4) + "\n" +
                        "\uD83D\uDCDE " + result.getString(5) + "\n" + "\uD83D\uDD70 " + result.getDate(6) + "\n" +
                        "\uD83C\uDF81 " + discount + "\n\n";

            }
            return line;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "Ошибка!";
        }
    }

    public String search_author(String author) {
        try {
            String line = "";
            PreparedStatement statement = null;
            String sql = "SELECT * FROM books where author like " + "\'"+author+"\'" ;
            statement = connection.prepareStatement(sql);
            result = statement.executeQuery();
            String discount = "";

            while (result.next()) {
                line = line + "\uD83D\uDCD9 " + result.getString(1) + "\n" + "\uD83D\uDD8B " + result.getString(2) + "\n" +
                        "\uD83D\uDCB8 " + result.getInt(3) + "\n" + "\uD83D\uDCB5 " + result.getInt(4) + "\n" +
                        "\uD83D\uDCAD " + result.getString(5) + "\n" + "\uD83D\uDD11 " + result.getInt(6) + "\n\n";

            }
            return line;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "Ошибка!";
        }
    }

    public String search_title(String title) {
        try {
            String line = "";
            PreparedStatement statement = null;
            String sql = "SELECT * FROM books where title like " + "\'"+title+"\'" ;
            statement = connection.prepareStatement(sql);
            result = statement.executeQuery();
            String discount = "";

            while (result.next()) {
                line = line + "\uD83D\uDCD9 " + result.getString(1) + "\n" + "\uD83D\uDD8B " + result.getString(2) + "\n" +
                        "\uD83D\uDCB8 " + result.getInt(3) + "\n" + "\uD83D\uDCB5 " + result.getInt(4) + "\n" +
                        "\uD83D\uDCAD " + result.getString(5) + "\n" + "\uD83D\uDD11 " + result.getInt(6) + "\n\n";

            }
            return line;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "Ошибка!";
        }
    }

    public String search_genre(String genre) {
        try {
            String line = "";
            PreparedStatement statement = null;
            String sql = "SELECT * FROM books where genre like " + "\'"+genre+"\'" ;
            statement = connection.prepareStatement(sql);
            result = statement.executeQuery();
            String discount = "";

            while (result.next()) {
                line = line + "\uD83D\uDCD9 " + result.getString(1) + "\n" + "\uD83D\uDD8B " + result.getString(2) + "\n" +
                        "\uD83D\uDCB8 " + result.getInt(3) + "\n" + "\uD83D\uDCB5 " + result.getInt(4) + "\n" +
                        "\uD83D\uDCAD " + result.getString(5) + "\n" + "\uD83D\uDD11 " + result.getInt(6) + "\n\n";

            }
            return line;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "Ошибка!";
        }
    }

    public String search_book_id(String book_id) {
        try {
            String line = "";
            PreparedStatement statement = null;
            String sql = "SELECT * FROM books where book_id = " +Integer.parseInt(book_id);
            statement = connection.prepareStatement(sql);
            result = statement.executeQuery();
            String discount = "";

            while (result.next()) {
                line = line + "\uD83D\uDCD9 " + result.getString(1) + "\n" + "\uD83D\uDD8B " + result.getString(2) + "\n" +
                        "\uD83D\uDCB8 " + result.getInt(3) + "\n" + "\uD83D\uDCB5 " + result.getInt(4) + "\n" +
                        "\uD83D\uDCAD " + result.getString(5) + "\n" + "\uD83D\uDD11 " + result.getInt(6) + "\n\n";

            }
            return line;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "Ошибка!";
        }
    }

    public String search_reader_id(String reader_id) {
        try {
            String line = "";
            PreparedStatement statement = null;
            String sql = "SELECT * FROM readers where reader_id = " +Integer.parseInt(reader_id) ;
            statement = connection.prepareStatement(sql);
            result = statement.executeQuery();
            String discount = "";

            while (result.next()) {
                if (result.getBoolean(7)) {
                    discount = "Есть скидка";
                } else {
                    discount = "Нет скидки";
                }
                line = line + "\uD83D\uDE42 " + result.getString(1) + "\n" + "\uD83D\uDC55 " + result.getString(2) + "\n" +
                        "\uD83E\uDE73 " + result.getString(3) + "\n" + "\uD83C\uDFE0 " + result.getString(4) + "\n" +
                        "\uD83D\uDCDE " + result.getString(5) + "\n" + "\uD83D\uDD70 " + result.getDate(6) + "\n" +
                        "\uD83C\uDF81 " + discount + "\n\n";

            }
            return line;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "Ошибка!";
        }
    }

    public String search_reader_id_in_issue(String reader_id) {
        try {
            String line = "";
            PreparedStatement statement = null;
            String sql = "SELECT * FROM issued_books where reader_id like " + "\'" + reader_id + "\'";
            statement = connection.prepareStatement(sql);
            result = statement.executeQuery();
            String discount = "";

            while (result.next()){
                line =  line  + "\uD83D\uDCD2 "+result.getString(1)+ "\n"+ "\uD83D\uDE4B\u200D♂️ "+  result.getString(2)+ "\n"+
                        "\uD83D\uDCE4 "+result.getDate(3)+ "\n" + "\uD83D\uDCE5 "+result.getDate(4) +"\n"+
                        "\uD83D\uDCB5 " +result.getInt(5)+ "ℹ️ " +result.getInt(6)+ "\n\n";

            }
            return line;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "Ошибка!";
        }
    }

    public String del_issue(String issue_id) {
        PreparedStatement statement = null;
        Integer iss_id = Integer.parseInt(issue_id);
        try {
            String sql = "DELETE FROM issued_books where issue_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, iss_id);
            statement.executeUpdate();
            return "Акт удален!";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "Ошибка!";
        }
    }

    public String del_reader(String reader_id) {
        PreparedStatement statement = null;
        Integer reader__id = Integer.parseInt(reader_id);
        try {
            String sql = "DELETE FROM readers where reader_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, reader__id);
            statement.executeUpdate();
            return "Читатель удален!";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "Ошибка!";
        }
    }

    public String del_book(String book_id) {
        PreparedStatement statement = null;
        Integer book__id = Integer.parseInt(book_id);
        try {
            String sql = "DELETE FROM books where book_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, book__id);
            statement.executeUpdate();
            return "Книга удалена!";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "Ошибка!";
        }
    }
}
