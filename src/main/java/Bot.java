import org.checkerframework.checker.units.qual.A;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bot extends TelegramLongPollingBot {
    private long chat_id;
    ArrayList<Long> id_chats = new ArrayList<Long>();
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    User user = new User();
    Login login = new Login();

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        chat_id = update.getMessage().getChatId();
        user.setChat_id(chat_id);

        String text = update.getMessage().getText();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        try {
            sendMessage.setText(getMessage(text, message, update));
            sendMessage.setChatId(user.getChat_id());
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getMessage(String msg, Message message, Update update) throws ParseException {
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        ArrayList<String> messages = new ArrayList<>();
        if (!id_chats.contains(chat_id)) {
            if (msg.equals("/start")) {
                return "Введите пароль!";
            }

            if (msg.equals("232032")) {
                id_chats.add(chat_id);
                return "Вход выполнен!\n\n" +
                        "Для начала работы напишите /go";
            }
        }

        if (id_chats.contains(chat_id)) {
            if (msg.equals("/go")) {
                keyboard.clear();
                keyboardFirstRow.clear();
                keyboardFirstRow.add("\uD83D\uDD0EПоиск");
                keyboardFirstRow.add("➕Добавление");
                keyboardFirstRow.add("❌Удаление");
                keyboardSecondRow.add("⤴️Добавить акт выдачи");
                keyboardSecondRow.add("ℹ️Поиск по ID");
                keyboard.add(keyboardFirstRow);
                keyboard.add(keyboardSecondRow);
                replyKeyboardMarkup.setKeyboard(keyboard);
                return "Выберите нужный раздел";
            }

            if (msg.equals("❌Удаление")) {
                keyboard.clear();
                keyboardFirstRow.clear();
                keyboardSecondRow.clear();
                keyboardFirstRow.add("❌Удалить акт выдачи");
                keyboardFirstRow.add("❌Удалить книгу");
                keyboardFirstRow.add("❌Удалить читателя");
                keyboardSecondRow.add("⬅️На главный экран");
                keyboard.add(keyboardFirstRow);
                keyboard.add(keyboardSecondRow);
                replyKeyboardMarkup.setKeyboard(keyboard);
                return "Выберите нужный раздел";
            }

            if (msg.equals("ℹ️Поиск по ID")) {
                keyboard.clear();
                keyboardFirstRow.clear();
                keyboardSecondRow.clear();
                keyboardFirstRow.add("ℹ️ID книги");
                keyboardFirstRow.add("ℹ️ID читателя");
                keyboardFirstRow.add("ℹ️ID читателя в актах выдачи");
                keyboardSecondRow.add("⬅️На главный экран");
                keyboard.add(keyboardFirstRow);
                keyboard.add(keyboardSecondRow);
                replyKeyboardMarkup.setKeyboard(keyboard);
                return "Выберите нужный раздел";
            }


            if (msg.equals("\uD83D\uDD0EПоиск")) {
                keyboard.clear();
                keyboardFirstRow.clear();
                keyboardSecondRow.clear();
                keyboardFirstRow.add("\uD83D\uDD0E\uD83D\uDCDAПоиск книги");
                keyboardFirstRow.add("\uD83D\uDD0E\uD83D\uDC68Поиск читателя");
                keyboardSecondRow.add("⬅️На главный экран");
                keyboard.add(keyboardFirstRow);
                keyboard.add(keyboardSecondRow);
                replyKeyboardMarkup.setKeyboard(keyboard);
                return "Выберите нужный раздел";
            }

            if (msg.equals("➕Добавление")) {
                keyboard.clear();
                keyboardFirstRow.clear();
                keyboardSecondRow.clear();
                keyboardFirstRow.add("➕\uD83D\uDCDAДобавить книгу");
                keyboardFirstRow.add("➕\uD83D\uDC68Добавить читателя");
                keyboardSecondRow.add("⬅️На главный экран");
                keyboard.add(keyboardFirstRow);
                keyboard.add(keyboardSecondRow);

                replyKeyboardMarkup.setOneTimeKeyboard(true);
                replyKeyboardMarkup.setKeyboard(keyboard);
                return "Выберите нужный раздел";
            }

            if (msg.equals("⬅️На главный экран")) {
                keyboard.clear();
                keyboardFirstRow.clear();
                keyboardFirstRow.add("\uD83D\uDD0EПоиск");
                keyboardFirstRow.add("➕Добавление");
                keyboardSecondRow.add("⤴️Добавить акт выдачи");
                keyboardSecondRow.add("ℹ️Поиск по ID");
                keyboard.add(keyboardFirstRow);
                keyboard.add(keyboardSecondRow);
                replyKeyboardMarkup.setKeyboard(keyboard);
                return "Выберите нужный раздел";
            }

            if (msg.equals("\uD83D\uDD0E\uD83D\uDCDAПоиск книги")) {
                keyboard.clear();
                keyboardFirstRow.clear();
                keyboardFirstRow.add("Поиск по названию");
                keyboardFirstRow.add("Поиск по автору");
                keyboardSecondRow.add("Поиск по жанру");
                keyboardSecondRow.add("⬅️На главный экран");
                keyboard.add(keyboardFirstRow);
                keyboard.add(keyboardSecondRow);
                replyKeyboardMarkup.setKeyboard(keyboard);
                return "Выберите нужный раздел";
            }

            if (msg.equals("\uD83D\uDD0E\uD83D\uDC68Поиск читателя")) {
                keyboard.clear();
                keyboardFirstRow.clear();
                keyboardFirstRow.add("Поиск по фамилии");
                keyboardFirstRow.add("Поиск по номеру телефона");
                keyboardSecondRow.add("⬅️На главный экран");
                keyboard.add(keyboardFirstRow);
                keyboard.add(keyboardSecondRow);
                replyKeyboardMarkup.setKeyboard(keyboard);
                return "Выберите нужный раздел";
            }

            if (msg.equals("ℹ️ID книги")) {
                replyKeyboardMarkup.setOneTimeKeyboard(true);
                replyKeyboardMarkup.setKeyboard(keyboard);

                return "Введите слово \"Номер книги\", затем в соответстии с примером:\n" +
                        "⚪️ID книги\n\n" +
                        "‼ПРИМЕР:Номер книги 12‼";
            }

            if (msg.equals("❌Удалить акт выдачи")) {
                replyKeyboardMarkup.setOneTimeKeyboard(true);
                replyKeyboardMarkup.setKeyboard(keyboard);

                return "Введите слово \"Удалить акт\", затем в соответстии с примером:\n" +
                        "⚪️ID акта выдачи\n\n" +
                        "‼ПРИМЕР:Удалить акт 2‼";
            }

            if (msg.equals("❌Удалить читателя")) {
                replyKeyboardMarkup.setOneTimeKeyboard(true);
                replyKeyboardMarkup.setKeyboard(keyboard);

                return "Введите слово \"Удалить читателя\", затем в соответстии с примером:\n" +
                        "⚪️ID читателя\n\n" +
                        "‼ПРИМЕР:Удалить читателя 2‼";
            }

            if (msg.equals("❌Удалить книгу")) {
                replyKeyboardMarkup.setOneTimeKeyboard(true);
                replyKeyboardMarkup.setKeyboard(keyboard);

                return "Введите слово \"Удалить книгу\", затем в соответстии с примером:\n" +
                        "⚪️ID книги\n\n" +
                        "‼ПРИМЕР:Удалить книгу 2‼";
            }

            if (msg.equals("ℹ️ID читателя в актах выдачи")) {
                replyKeyboardMarkup.setOneTimeKeyboard(true);
                replyKeyboardMarkup.setKeyboard(keyboard);

                return "Введите слово \"Номер в актах\", затем в соответстии с примером:\n" +
                        "⚪️ID читателя\n\n" +
                        "‼ПРИМЕР:Номер в актах 5‼";
            }

            if (msg.equals("ℹ️ID читателя")) {
                replyKeyboardMarkup.setOneTimeKeyboard(true);
                replyKeyboardMarkup.setKeyboard(keyboard);

                return "Введите слово \"Номер читателя\", затем в соответстии с примером:\n" +
                        "⚪️ID читателя\n\n" +
                        "‼ПРИМЕР:Номер читателя 6‼";
            }

            if (msg.equals("Поиск по фамилии")) {
                replyKeyboardMarkup.setOneTimeKeyboard(true);
                replyKeyboardMarkup.setKeyboard(keyboard);

                return "Введите слово \"Фамилия\", затем в соответстии с примером:\n" +
                        "⚪️Фамилия пользователя\n\n" +
                        "‼ПРИМЕР:Фамилия Иванов‼";
            }

            if (msg.equals("Поиск по номеру телефона")) {
                replyKeyboardMarkup.setOneTimeKeyboard(true);
                replyKeyboardMarkup.setKeyboard(keyboard);

                return "Введите слово \"Телефон\", затем в соответстии с примером:\n" +
                        "⚪️Номер телефона пользователя\n\n" +
                        "‼ПРИМЕР:Телефон 79991234545‼";
            }

            if (msg.equals("Поиск по названию")) {
                replyKeyboardMarkup.setOneTimeKeyboard(true);
                replyKeyboardMarkup.setKeyboard(keyboard);

                return "Введите слово \"Название\", затем в соответстии с примером:\n" +
                        "⚪️Название книги\n\n" +
                        "‼ПРИМЕР:Название Вий‼";
            }

            if (msg.equals("Поиск по жанру")) {
                replyKeyboardMarkup.setOneTimeKeyboard(true);
                replyKeyboardMarkup.setKeyboard(keyboard);

                return "Введите слово \"Жанр\", затем в соответстии с примером:\n" +
                        "⚪️Жанр\n\n" +
                        "‼ПРИМЕР:Жанр Фантастика‼";
            }

            if (msg.equals("Поиск по автору")) {
                replyKeyboardMarkup.setOneTimeKeyboard(true);
                replyKeyboardMarkup.setKeyboard(keyboard);

                return "Введите слово \"Автор\", затем в соответстии с примером:\n" +
                        "⚪️Автор книги\n\n" +
                        "‼ПРИМЕР:Автор Гоголь Н.В.";
            }

            if (msg.equals("⤴️Добавить акт выдачи")) {
                replyKeyboardMarkup.setOneTimeKeyboard(true);
                replyKeyboardMarkup.setKeyboard(keyboard);

                return "Введите слово \"Акт выдачи\", затем в соответстии с примером:\n" +
                        "⚪️Номер книги\n⚪️Номер читателя\n⚪️Дата возврата\n\n" +
                        "‼ПРИМЕР:Акт выдачи 12 9 2021-08-15‼";
            }

            if (msg.equals("➕\uD83D\uDC68Добавить читателя")) {

                replyKeyboardMarkup.setOneTimeKeyboard(true);
                replyKeyboardMarkup.setKeyboard(keyboard);

                return "Введите слово \"Читатель\", затем в соответстии с примером:\n" +
                        "⚪️Фамилия\n⚪️Имя\n⚪️Отчество\n⚪️Адрес проживания\n⚪️Номер телефона\n⚪️Дата регистрации\n\n " +
                        "‼ПРИМЕР:Читатель Иванов Петр Михайлович Гагарина 23, 54 79991234545 2021-05-23‼";
            }

            if (msg.equals("➕\uD83D\uDCDAДобавить книгу")) {
                replyKeyboardMarkup.setOneTimeKeyboard(true);
                replyKeyboardMarkup.setKeyboard(keyboard);

                return "Введите слово \"Книга\", затем в соответстии с примером:\n" +
                        "⚪️Стоимость книги\n⚪️Жанр\n⚪️Название\n⚪️Автор\n\n " +
                        "‼ПРИМЕР:Книга 2000 Фантастика \"Вий\" Гоголь Н.В.‼";
            }

            if (msg.contains("книга") | msg.contains("Книга")) {
                String input_data = msg;
                Pattern pattern = Pattern.compile("(\\w+)\\s+(.+)\\s+(.+)\\s+\\\"(.+)\\\"\\s+(.+)", Pattern.UNICODE_CHARACTER_CLASS
                        | Pattern.CASE_INSENSITIVE);
                String value = "";
                String genre = "";
                String name = "";
                String author = "";
                Matcher matcher = pattern.matcher(input_data);
                if (matcher.matches()) {
                    value = matcher.group(2);
                    genre = matcher.group(3);
                    name = matcher.group(4);
                    author = matcher.group(5);
                }
                Integer col_value = Integer.parseInt(value.trim());
                try {
                    login.add_new_book(name, author, col_value, genre);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return "Ошибка! Книга не добавлена";
                }
                return "Книга добавлена";
            }

            if (msg.contains("читатель") | msg.contains("Читатель")) {
                String input_data = msg;
                String[] arrSplit = input_data.split(" ");
                String last_name = arrSplit[1];
                String first_name = arrSplit[2];
                String middle_name = arrSplit[3];
                String address = arrSplit[4] + " " + arrSplit[5] + " " + arrSplit[6];
                String tel_number = arrSplit[7];
                String date_of_registration = arrSplit[8];

                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(date_of_registration);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                final String stringDate = dateFormat.format(date);
                java.sql.Date sqlDate = java.sql.Date.valueOf(stringDate);

                try {
                    login.add_new_reader(last_name, first_name, middle_name, address, tel_number, sqlDate);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return "Ошибка! Читатель не добавлен";
                }
                return "Читатель добавлен";
            }

            if (msg.contains("Акт выдачи") | msg.contains("акт Выдачи") | msg.contains("Акт Выдачи") | msg.contains("акт выдачи")) {
                String input_data = msg;
                String[] arrSplit = input_data.split(" ");
                String book_id = arrSplit[2];
                String reader_id = arrSplit[3];
                String return_date = arrSplit[4];

                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(return_date);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                final String stringDate = dateFormat.format(date);
                java.sql.Date sqlDate = java.sql.Date.valueOf(stringDate);

                try {
                    login.add_new_issue(book_id, reader_id, sqlDate);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return "Ошибка! Акт не записан";
                }
                return "Акт выдачи записан";
            }

            if (msg.contains("фамилия") | msg.contains("Фамилия")) {
                String input_data = msg;
                String[] arrSplit = input_data.split(" ");
                String last_name = arrSplit[1];
                try {
                    return login.search_last_name(last_name);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return "Ошибка!";
                }

            }

            if (msg.contains("телефон") | msg.contains("Телефон")) {
                String input_data = msg;
                String[] arrSplit = input_data.split(" ");
                String tel_number = arrSplit[1];
                try {
                    return login.search_tel_number(tel_number);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return "Ошибка!";
                }

            }

            if (msg.contains("автор") | msg.contains("Автор")) {
                String input_data = msg;
                String[] arrSplit = input_data.split(" ");
                String author = arrSplit[1];
                author = author + arrSplit[2];
                try {
                    return login.search_author(author);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return "Ошибка!";
                }

            }

            if (msg.contains("Название") | msg.contains("название")) {
                String input_data = msg;
                String[] arrSplit = input_data.split(" ", 2);
                String title = arrSplit[1];

                try {
                    return login.search_title(title);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return "Ошибка!";
                }

            }

            if (msg.contains("Жанр") | msg.contains("жанр")) {
                String input_data = msg;
                String[] arrSplit = input_data.split(" ", 2);
                String genre = arrSplit[1];

                try {
                    return login.search_genre(genre);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return "Ошибка!";
                }

            }

            if (msg.contains("Номер читателя") | msg.contains("номер Читателя") | msg.contains("номер читателя") | msg.contains("Номер Читателя")) {
                String input_data = msg;
                String[] arrSplit = input_data.split(" ");
                String reader_id = arrSplit[2];

                try {
                    return login.search_reader_id(reader_id);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return "Ошибка!";
                }

            }

            if (msg.contains("Номер книги") | msg.contains("номер Книги") | msg.contains("номер книги") | msg.contains("Номер Книги")) {
                String input_data = msg;
                String[] arrSplit = input_data.split(" ");
                String book_id = arrSplit[2];

                try {
                    return login.search_book_id(book_id);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return "Ошибка!";
                }

            }

            if (msg.contains("Номер в актах") | msg.contains("номер в Актах") | msg.contains("номер в актах") | msg.contains("Номер в Актах")) {
                String input_data = msg;
                String[] arrSplit = input_data.split(" ");
                String reader_id = arrSplit[3];

                try {
                    return login.search_reader_id_in_issue(reader_id);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return "Ошибка!";
                }

            }

            if (msg.contains("Удалить акт") | msg.contains("Удалить Акт") | msg.contains("удалить акт") | msg.contains("удалить Акт")) {
                String input_data = msg;
                String[] arrSplit = input_data.split(" ");
                String issue_id = arrSplit[2];

                try {
                    return login.del_issue(issue_id);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return "Ошибка!";
                }

            }

            if (msg.contains("Удалить книгу") | msg.contains("Удалить Книгу") | msg.contains("удалить книгу") | msg.contains("удалить Книгу")) {
                String input_data = msg;
                String[] arrSplit = input_data.split(" ");
                String book_id = arrSplit[2];

                try {
                    return login.del_book(book_id);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return "Ошибка!";
                }
            }

            if (msg.contains("Удалить читателя") | msg.contains("Удалить Читателя") | msg.contains("удалить читателя") | msg.contains("удалить Читателя")) {
                String input_data = msg;
                String[] arrSplit = input_data.split(" ");
                String reader_id = arrSplit[2];

                try {
                    return login.del_reader(reader_id);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return "Ошибка!";
                }

            }


            return "Ошибка! Проверьте правильность введенных данных";

        }
        return "Неверный пароль!";
    }



    @Override
    public String getBotUsername() {
        return "BookStoreandRentalBot";
    }

    @Override
    public String getBotToken() {
        return "1837406648:AAFEVL0AEaL7phmdg0HceKaEvE0n0eJzHVo";
    }

}
