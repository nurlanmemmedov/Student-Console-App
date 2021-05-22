package Utils;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * us used for taking an input from user
 */
public class InputReader {
    public static Scanner scanner = new Scanner(System.in);
    public static int readInt(String message ,int min, int max){
        int choice;
        while (true){
            try {
                System.out.print(message);
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <=max){
                    break;
                }
                System.out.println("Please type number between "+min+" and "+ max);
            }catch (Exception e){
                System.out.println("Please type number between "+min+" and "+ max);
            }
        }
        return choice;
    }


    public static String emailReader(String message){
        String email = new String();
        Pattern pattern = Pattern.compile("^([\\w\\.\\-]+)@([\\w\\-]+)((\\.(\\w){2,3})+)$");
        while (true){
            System.out.print(message);
            email = scanner.nextLine();
            Matcher matcher = pattern.matcher(email);


            if(matcher.matches()){
                break;
            }
            else{
                System.out.println("email is incorrect, please type again");
            }
        }
        return email;
    }

    public static String phoneReader(String message){
        String email = new String();
        Pattern pattern = Pattern.compile("^\\+994(50|51|55|70|77|99)[1-9]\\d{6}$");
        while (true){
            System.out.print(message);
            email = scanner.nextLine();
            Matcher matcher = pattern.matcher(email);

            if(matcher.matches()){
                break;
            }
            else{
                System.out.println("phone number is incorrect. please type again in this format +994*********");
            }
        }
        return email;
    }

    public static String readString(String message){
        String str = new String();
        while (true){
            System.out.print(message);
            str = scanner.nextLine();
            if (str.matches("[a-zA-Z]{2,20}")){
                break;
            }
            else{
                System.out.println("input can't be empty and can't consist of numbers");
            }
        }
        return str;
    }

}
