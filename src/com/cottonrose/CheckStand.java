package com.cottonrose;

/**
 * Auther:secondriver
 * Created: ${date}
 */

import com.sun.org.apache.xpath.internal.operations.Or;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;


public class CheckStand {

    public  static Scanner reader = new Scanner(System.in);
    public static void main(String[] args) {
        Boolean condi = true;
        GoodsCenter.initGoodsArray();
        while (condi) {
            beginMune();
            System.out.println("请选择您的操作：");
            String id = reader.nextLine();
            switch (id) {
                case "S":
                case "s": {
                    owner();
                    continue;
                }
                case "C":
                case "c": {
                    customer();
                    break;
                }
                case "A":
                case "a": {
                    abort();
                    break;
                }
                case "Q":
                case "q": {
                    quitMenu();
                    condi = false;
                    break;
                }
                default: {
                    System.out.println("输入有误，请重新输入：");
                    break;
                }
            }
        }
        reader.close();
    }

    //开始菜单
    public static void beginMune() {
        System.out.println("*********************************");
        System.out.println("******welcome to the market******");
        System.out.println("*********************************");
        System.out.println("*********************************");
        System.out.println("*****[S]店主         [C]顾客*****");
        System.out.println("*****[A]关于         [Q]退出*****");
        System.out.printf("*********************************\n\n");
    }

    //退出菜单
    public static void quitMenu() {
        System.out.println("*********************************");
        System.out.println("***********欢迎下次光临**********");
        System.out.printf("*********************************\n\n");
    }

    //读取输入流信息
    public static Goods readGoods(){

        while(true){
            String goods = reader.nextLine();
            goods = goods.trim();
            String[] goodInfo = goods.split(" ");
            if(goodInfo!=null && goodInfo.length==6 || goodInfo.length==2){
                if(goodInfo.length==6) {
                    Goods good = new Goods(
                            Integer.parseInt(goodInfo[0]),
                            goodInfo[1],
                            Float.parseFloat(goodInfo[2]),
                            Integer.parseInt(goodInfo[3]),
                            LocalDate.parse(goodInfo[4].subSequence(0,goodInfo[4].length())),
                            LocalDate.parse(goodInfo[5].subSequence(0,goodInfo[5].length())),
                            LocalDate.now()
                    );
                    return good;
                }
                if(goodInfo.length==2){
                    Goods good = new Goods(Integer.parseInt(goodInfo[0]),goodInfo[1]);
                    return good;
                }
            }else{
                System.out.println("格式有误，请重新输入");
            }
        }
    }

    //店主操作
    public static void owner() {
        boolean con = true;
        while (con){
            ownerMenu();
            int num = GoodsCenter.isToTime();
            if(num!=0){
                System.out.printf("上架商品中出现过期商品共"+num+"件，请及时下架！\n");
            }
            System.out.println("请选择您的操作：");
            String op = reader.nextLine();
            switch (op) {
                case "P":
                case "p": {
                    GoodsCenter.printGoods();
                    System.out.println("请输入上架商品信息：格式如下：(不用填写上架日期)");
                    System.out.println("1 面包 5 20 2018-02-03 2018-11-15");
                    Goods goods = readGoods();
                    GoodsCenter.addGood(goods);
                    GoodsCenter.printGoods();
                    break;
                }
                case "D":
                case "d": {
                    GoodsCenter.printGoods();
                    System.out.println("请输入下架商品编号及商品名称：格式如下：");
                    System.out.println("1 面包");
                    Goods goods = readGoods();
                    GoodsCenter.deleteGood(goods);
                    GoodsCenter.printGoods();
                    break;
                }
                case "M":
                case "m": {
                    GoodsCenter.printGoods();
                    System.out.println("请输入要修改的商品编号及商品名称：格式如下：");
                    System.out.println("1 面包");
                    Goods goods = readGoods();
                    GoodsCenter.modifyGood(goods);
                    GoodsCenter.printGoods();
                    break;
                }
                case "Q":
                case "q": {
                    con = false;
                    break;
                }
                default: {
                    System.out.println("输入有误，请重新输入：");
                    break;
                }
            }
        }
    }

    //店主菜单
    public static void ownerMenu() {
        System.out.println("*********************************");
        System.out.println("*****          店主         *****");
        System.out.println("*********************************");
        System.out.println("*****[P]上架         [D]下架*****");
        System.out.println("*****[M]修改         [Q]退出*****");
        System.out.printf("*********************************\n\n");
    }

    //关于
    public static void abort(){
        System.out.println("*********************************");
        System.out.println("****    author:cottonrose    ****");
        System.out.println("****e-mail：1320210577@qq.com****");
        System.out.println("****      version:1.1.0      ****");
        System.out.println("****     date:2018-12-01     ****");
        System.out.printf("*********************************\n\n");
    }

    //顾客操作
    public static void customer(){
        boolean con = true;
        Order order = new Order();
        while(con){
            Scanner input = new Scanner(System.in);
            customerMenu();
            switch (input.nextLine()){
                case"A":
                case"a":{
                    GoodsCenter.printGoods();
                    System.out.println("请选择要购买的商品：格式如下：");
                    System.out.println("1 2 (商品编号 数量)");
                    String[] info = input.nextLine().split(" ");
                    if(info!=null && info.length==2){
                        Goods goods = GoodsCenter.getGood(Integer.parseInt(info[0]));
                        if(goods!=null){
                            order.add(Integer.parseInt(info[0]),Integer.parseInt(info[1]));
                            order.printOrder();
                        }else{
                            System.out.println("商品不存在，请重新选择");
                            continue;
                        }
                    }else{
                        System.out.println("输入格式有误，请重新输入");
                        continue;
                    }
                    break;
                }
                case"S":
                case"s":{
                    order.printOrder();
                    break;
                }
                case"M":
                case"m":{
                    System.out.println("请选择需要修改的商品：格式如下：");
                    System.out.println("1 2 (商品编号 要减少的数量)");
                    String[] info = input.nextLine().split(" ");
                    if(info!=null && info.length==2){
                        if(order.isInOrder(Integer.parseInt(info[0]))){
                            order.modify(Integer.parseInt(info[0]),Integer.parseInt(info[1]));
                            order.printOrder();
                        }else{
                            System.out.println("该商品目前不在商品订单中，请重新输入");
                            continue;
                        }
                    }else{
                        System.out.println("输入格式有误，请重新输入");
                        continue;
                    }

                    break;
                }
                case"Q":
                case"q":{
                    con = false;
                    break;
                }
                default:{
                    System.out.println("输入有误，请重新输入");
                }
            }
            input.close();
        }
    }

    //顾客菜单
    public static void customerMenu(){
        System.out.println("*********************************");
        System.out.println("*****          顾客         *****");
        System.out.println("*********************************");
        System.out.println("*****[A]选购         [S]结算*****");
        System.out.println("*****[M]修改         [Q]退出*****");
        System.out.printf("*********************************\n\n");
    }

}

