package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author HongYun on 2023/1/2
 */

public class View {

    public static void main(String[] args) throws IOException {

        showView();
        System.out.println();
        System.out.println("system exited.");
    }
    public static void showView() throws IOException {

        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String actionType = "";
        String id = "";
        String password = "";
        String actionTypeTwo = "";
        boolean loop = true;
        while (loop){
            System.out.println("==============登录选项：=============");
            System.out.println("\t\t" + "1 : 登录");
            System.out.println("\t\t" + "9 : 退出");
            actionType = read.readLine();
            switch (actionType){
                case "1" : {
                    System.out.println("\t\tplease input your id");
                    id = read.readLine();
                    System.out.println("\t\tplease input your password");
                    password = read.readLine();
                    if(password.equals("1111")){
                        System.out.println("welcome " + id);
                        while (loop){
                            System.out.println("--------------message system acton--------------");
                            System.out.println("\t\t1 : show all online users");
                            System.out.println("\t\t2 : send message to group");
                            System.out.println("\t\t3 : send message to personal");
                            System.out.println("\t\t4 : send file");
                            System.out.println("\t\t5 : exit system");
                            System.out.println("Please input your choice");
                            actionTypeTwo = read.readLine();
                            switch (actionTypeTwo){
                                case "1" : {
                                    System.out.println("\t\tshow all online users");
                                    break;
                                }
                                case "2" : {
                                    System.out.println("\t\tsend message to group");
                                    break;
                                }
                                case "3" : {
                                    System.out.println("\t\tsend message to personal");
                                    break;
                                }
                                case "4" : {
                                    System.out.println("\t\tsend file");
                                    break;
                                }
                                case "5" : {
                                    loop = false;
                                    System.out.println("\t\tlogin out");
                                    break;
                                }
                            }
                        }
                    }else {
                        System.out.println("=========login in failed..============");
                    }
                    break;
                }
                case "9" : {
                    System.out.println(id + " \t\tlogin out");
                    loop = false;
                    break;
                }
            }
        }
    }
}
