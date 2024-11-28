package lk.ijse.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {




        public static boolean isTextFieldValid(TextField textField, String text){
            String filed = switch (textField) {
                case NAME -> "^[A-Za-z]+(?: [A-Za-z]+)*$";
                case ADDRESS -> "^([A-z0-9]|[-\\,.@+]|\\\\s){4,}$";
                case TEL -> "^[0]([1-9]{2})([0-9]){7}$";
                case DURATION -> "\\d{1,2}\\s?(month|year)s?"
                ;
                case FEE -> "^([0-9]){1,}[.]([0-9]){1,}$";
                case STUDENTID -> "^S\\d{3}$";
                case PROGRAMID -> "^C\\d{4}$";
            };

            Pattern pattern = Pattern.compile(filed);

            if (text != null){
                if (text.trim().isEmpty()){
                    return false;
                }
            }else {
                return false;
            }

            Matcher matcher = pattern.matcher(text);

            if (matcher.matches()){
                return true;
            }
            return false;
        }
        public static boolean setTextColor(TextField location, javafx.scene.control.TextField textField) {
            if (lk.ijse.util.Regex.isTextFieldValid(location, textField.getText())) {
                textField.setStyle("-fx-border-color: transparent; -fx-border-radius: 0; -fx-border-width: 0; -fx-focus-color: transparent;");
                return true;
            } else {
                textField.setStyle("-fx-border-color: red; -fx-border-radius: 5; -fx-border-width: 3;");
                return false;
            }
        }

    }


