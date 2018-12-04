package com.cottonrose;

import java.time.LocalDate;
import java.util.Scanner;
import java.time.LocalTime;

/**
 * Auther:secondriver
 * Created: ${date}
 */ //商品信息类
class Goods{
    private int no; //商品货号
    private String name; //商品名称
    private float price; //商品价格
    private int num; //商品数量
    private LocalDate toTime; //截止日期
    private LocalDate proTime; //生产日期
    private LocalDate upTime; //上架日期

    //构造方法
    public Goods(int no, String name, float price, int num, LocalDate proTime, LocalDate toTime, LocalDate upTime) {
        this.no = no;
        this.name = name;
        this.price = price;
        this.num = num;
        this.proTime = proTime;
        this.toTime = toTime;
        this.upTime = upTime;
    }
    public Goods(int no,String name){
        this.no = no;
        this.name = name;
    }

    public int getNo() {

        return this.no;
    }

    public String getName() {

        return this.name;
    }

    public float getPrice() {

        return this.price;
    }

    public int getNum() {

        return this.num;
    }

    public LocalDate getUpTime() {

        return this.upTime;
    }

    public LocalDate getProTime() {
        return this.proTime;
    }

    public LocalDate getToTime() {
        return this.toTime;
    }

    public void setNo(int no) {

        this.no = no;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setPrice(float price) {


        this.price = price;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setUpTime(LocalDate upTime) {

        this.upTime = upTime;
    }

    public void setProTime(LocalDate proTime) {

        this.proTime = proTime;
    }

    public void setToTime(LocalDate toTime) {

        this.toTime = toTime;
    }
}

//商品位
class GoodsCenter{
    private static int maxGoods = 10; //最大容量
    private static Goods[] goodsArray; //商品信息数组
    //初始化商品存储容器
    public static void initGoodsArray(){
        goodsArray = new Goods[maxGoods];
        int i = 0;
        while(i<goodsArray.length){
            goodsArray[i] = new Goods(i+1,"——",0.0F,0,
                    LocalDate.of(1999,1,1),
                    LocalDate.of(1999,1,1),
                    LocalDate.of(1999,1,1));
            i++;
        }
    }
    //添加商品
    public static void addGood(Goods goods){
        if(isFull()){
            System.out.println("商品架已满，不能上架");
            return;
        }
        if(isExit(goods)){
            System.out.println("该商品已存在，不能上架");
            return;
        }
        Goods tmp = goodsArray[goods.getNo()-1];
        if(tmp.getNo()==goods.getNo()){
            tmp.setName(goods.getName());
            tmp.setPrice(goods.getPrice());
            tmp.setNum(goods.getNum());
            tmp.setUpTime(goods.getUpTime());
            tmp.setProTime(goods.getProTime());
            tmp.setToTime(goods.getToTime());
            System.out.println("添加成功");
        }else{
            return;
        }
    }
    //删除商品
    public static void deleteGood(Goods goods){
        if(isEmpty()){
            System.out.println("商品位全为空，删除失败");
            return;
        }
        if(isExit(goods)){
        Goods tmp = goodsArray[goods.getNo()-1];
            if(tmp.getNo()==goods.getNo()){
                tmp.setName("——");
                tmp.setPrice(0.0F);
                tmp.setNum(0);
                tmp.setUpTime(LocalDate.of(1999,1,1));
                tmp.setProTime(LocalDate.of(1999,1,1));
                tmp.setToTime(LocalDate.of(1999,1,1));
                System.out.println("删除成功");
            }
        }else{
            System.out.println("该商品不存在，删除失败");
        }
    }
    //修改商品信息
    public static void modifyGood(Goods goods){
        if(isEmpty()){
            System.out.println("商品位全为空，修改失败");
            return;
        }
        if(isExit(goods)){
            boolean con = true;
            while (con){
                System.out.println("请选择要修改的商品属性：");
                System.out.println("a.单价 b.库存 q.退出修改");
                Scanner sc= new Scanner(System.in);
                switch (sc.nextLine()){
                    case"a":
                    case"A":{
                        System.out.println("请输入要修改的单价：如：10.0");
                        float price = sc.nextFloat();
                        goodsArray[goods.getNo()-1].setPrice(price);
                        break;
                    }
                    case"b":
                    case"B": {
                        System.out.println("请输入要修改的库存数量：如：10");
                        int num = sc.nextInt();
                        goodsArray[goods.getNo() - 1].setNum(num);
                        break;
                    }
                    case"q":
                    case"Q":{
                        con = false;
                        break;
                    }
                    default:{
                        System.out.println("输入有误，请重新输入");
                    }
                }
            }
            System.out.println("修改成功");
        }else{
            System.out.println("该商品不存在，删除失败");
        }
    }
    //商品是否已满
    public static boolean isFull(){
        for(int i=0; i<goodsArray.length;i++){
            if(goodsArray[i].getName().equals("——")){
                return false;
            }
        }
        return true;
    }
    //商品位是否全为空
    public static boolean isEmpty(){
        for(int i=0; i<goodsArray.length;i++){
            if(goodsArray[i].getName().equals("——")){
                continue;
            }else{
                return false;
            }
        }
        return true;
    }
    //商品是否存在
    public static boolean isExit(Goods goods){
        for(int i=0;i<goodsArray.length;i++){
            Goods tmp = goodsArray[i];
            if(tmp.getNo()==goods.getNo() && tmp.getName().equals(goods.getName())){
                return true;
            }
        }
        return false;
    }
    //打印商品信息
    public static void printGoods(){
        System.out.println("========================================================");
        System.out.println("No   商品    单价   库存   上架时间   生产日期   到期日期");
        System.out.println("========================================================");
        for(int i=0; i<goodsArray.length; i++){
            if(i+1<10){
                System.out.print(" "+goodsArray[i].getNo()+" ");
            }else{
                System.out.print(goodsArray[i].getNo()+" ");
            }
            System.out.printf("%-8s",goodsArray[i].getName());
            System.out.printf("%-7.2f ",goodsArray[i].getPrice());
            System.out.printf("%5d ",goodsArray[i].getNum());
            System.out.print("   "+goodsArray[i].getUpTime().toString()+" ");
            System.out.print(goodsArray[i].getProTime().toString()+" ");
            System.out.println(goodsArray[i].getToTime().toString()+" ");
        }
        System.out.printf("========================================================\n");
    }
    //判断有几件商品到期了
    public static int isToTime(){
        int count = 0;
        for(int i=0; i<goodsArray.length; i++){
            if(!goodsArray[i].getName().equals("——")){
                LocalDate date = LocalDate.now();
                if(!date.isBefore(goodsArray[i].getToTime())){
                    count++;
                }
            }
        }
        return count;
    }
    //返回商品
    public static Goods getGood(int id){
        for(int i=0; i<goodsArray.length; i++){
            Goods tmp = goodsArray[i];
            if(tmp.getNo()==id && !tmp.getName().equals("——")){
                return goodsArray[i];
            }
        }
        return null;
    }
    public static int getMaxGoods(){
        return maxGoods;
    }


}

//订单
class Order{
    private static int orderId = 0;
    private int id;
    private Goods[] cargo;
    private int[] cargoNumber;

    public Order(){
        this.id = ++orderId;
        this.cargo = new Goods[GoodsCenter.getMaxGoods()];
        this.cargoNumber = new int[GoodsCenter.getMaxGoods()];
    }
    //添加商品
    public void add(int id, int num){
        int index = id-1;
        this.cargo[index] = GoodsCenter.getGood(id);
        this.cargoNumber[index] += num;
    }
    //修改商品订单
    public void modify(int id, int num){
        int index = id-1;
        int number = this.cargoNumber[index]-num;
        if(number>0){
            this.cargoNumber[index] = number;
        }else{
            this.cargo[index] = null;
            this.cargoNumber[index] = 0;
        }
        System.out.println("修改成功");
    }
    //商品是否在订单里
    public boolean isInOrder(int id){
        if(this.cargo[id-1]!=null){
            return true;
        }
        return false;
    }
    //计算商品价钱
    public double getPrice(){
        double price = 0;
        for(int i=0;i<this.cargo.length; i++){
            if(this.cargo[i]!=null){
                price += this.cargoNumber[i]*this.cargo[i].getPrice();
            }
        }
        return price;
    }
    //打印商品订单
    public void printOrder(){
        System.out.println("==============================");
        System.out.println("单号："+id);
        System.out.println("打印日期："+LocalDate.now().toString());
        System.out.println("==============================");
        System.out.println("No   商品   单价   数量  合计");
        for(int i=0; i<this.cargo.length; i++){
            Goods tmp = this.cargo[i];
            if(tmp!=null){
                int count = this.cargoNumber[i];
                if(count>0){
                    System.out.printf("%d %-5s %-6.2f %-5d\n",
                            tmp.getNo(),tmp.getName(),tmp.getPrice(),this.cargoNumber[i]);
                }else{
                    continue;
                }
            }
        }
        System.out.printf("总计：%7.2f\n",this.getPrice());
        System.out.printf("==============================\n\n");

    }

}