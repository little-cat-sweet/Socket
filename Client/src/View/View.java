package View;

import Common.Message;
import Service.ClientService;
import Service.MessageClientService;
import Service.impl.ClientServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author HongYun on 2023/1/2
 */

public class View {

    private static ClientService clientService = new ClientServiceImpl();
    private static MessageClientService messageClientService = new MessageClientService();
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
                    if(clientService.checkUser(id, password)){
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
                                    clientService.getOnlineUserReq();
                                    break;
                                }
                                case "2" : {
                                    System.out.println("\t\tsend message to group");
                                    break;
                                }
                                case "3" : {
                                    System.out.println("\t\t input your receiver");
                                    String receiver = read.readLine();
                                    System.out.println("\t\t input your content");
                                    String content = read.readLine();
                                    messageClientService.sendMessageToOne(content, id, receiver);
                                    break;
                                }
                                case "4" : {
                                    System.out.println("\t\tsend file");
                                    break;
                                }
                                case "5" : {
                                    clientService.logout();
                                    loop = false;
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
