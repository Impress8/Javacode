package com.itheima.demo;

import java.util.Scanner;

public class ShopCarTest {
    public static void main(String[] args) {
        //1、定义商品类，用于后期创建对象
        //2、定义购物车对象，使用一个数组对象表示
        Goods[] shopCar = new Goods[100];
        while (true) {
            System.out.println("请您选择如下命令进行操作：");
            System.out.println("添加商品到购物车：add");
            System.out.println("查询购物车商品展示：query");
            System.out.println("修改商品购买数量：update");
            System.out.println("结算购买商品的金额：pay");
            Scanner sc = new Scanner(System.in);
            System.out.println("请您输入命令：");
            String commend = sc.next();
            switch (commend) {
                case "add":
                    addGoods(shopCar, sc);
                    break;
                case "query":
                    queryGoods(shopCar);
                    break;
                case "update":
                    updateGoods(shopCar, sc);
                    break;
                case "pay":
                    payGoods(shopCar);
                    break;
                default:
                    System.out.println("input error!");
            }
        }

    }

    public static void addGoods(Goods[] shopCar, Scanner sc) {
        //1、录入用户输入的购买的商品的信息
        System.out.println("请您输入购买商品的编号（不重复）：");
        int id = sc.nextInt();
        System.out.println("请您输入购买商品的名称：");
        String name = sc.next();
        System.out.println("请您输入购买商品的价格：");
        double price = sc.nextDouble();
        System.out.println("请您输入购买商品的数量：");
        int buynumber = sc.nextInt();
        //2、把商品信息封装成商品对象
        Goods g = new Goods();
        g.buynumber = buynumber;
        g.id = id;
        g.name = name;
        g.price = price;
        //3、把商品对象添加到购物车数组中
        //遍历数组，添加至最后一位
        for (int i = 0; i < shopCar.length; i++) {
            if (shopCar[i] == null) {
                shopCar[i] = g;
                break;
            }

        }
        System.out.println("您的商品" + name + "添加购物车完成！");

    }

    public static void queryGoods(Goods[] shopCar) {
        System.out.println("========查询购物车信息如下========");
        System.out.println("编号\t\t名称\t\t价格\t\t购买数量");
        for (int i = 0; i < shopCar.length; i++) {
            Goods g = shopCar[i];
            if (g != null) {
                //展示这个商品对象
                System.out.println(g.id + "\t\t" + g.name + "\t\t" + g.price + "\t\t" + g.buynumber);
            } else {
                //遍历结束


                break;
            }

        }


    }

    public static void updateGoods(Goods[] shopCar, Scanner sc) {
        //让用户输入要修改商品的id，根据id查询出要修改的商品对象
        while (true) {
            System.out.println("请您输入要修改的商品id：");
            int id = sc.nextInt();
            Goods g = getGoodsid(shopCar, id);
            if (g == null) {
                System.out.println("对不起，没有购买该商品！");
            } else {
                System.out.println("请您输入" + g.name + "最新购买数量");
                int buynumber = sc.nextInt();
                g.buynumber = buynumber;
                System.out.println("修改完成！");
                queryGoods(shopCar);
                break;
            }
        }
    }

    public static Goods getGoodsid(Goods[] shopCar, int id) {
        for (int i = 0; i < shopCar.length; i++) {
            Goods g = shopCar[i];
            if (g != null) {
                //判断这个商品id是否是我们要找的
                if (g.id == id) {
                    return g;
                }
            } else {
                return null;//找完已存在的商品，都没有找到
            }

        }
        return null;//代表找完了所有100个商品也没有该商品
    }

    public static void payGoods(Goods[] shopCar) {
        queryGoods(shopCar);
        double money = 0;
        for (int i = 0; i < shopCar.length; i++) {
            Goods g = shopCar[i];
            if (g != null) {
                money += (g.price * g.buynumber);
            } else {
                break;
            }
        }
        System.out.println("订单总金额为：" + money);
    }
}
