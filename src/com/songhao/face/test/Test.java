package com.songhao.face.test;

import com.songhao.face.util.FaceUtil;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Lenovo\\Pictures\\new\\yeji2.jpg");
        String str = null;
        try {
            str = FaceUtil.detect(file);//人脸的检测
            System.out.println("face_token:"+str);
            //添加一个人脸到集合中
//            boolean addRes = FaceUtil.addFace(str);
            //添加结果
//            System.out.println("添加结果："+addRes);
            boolean res = FaceUtil.search(str);//人脸的搜索
            System.out.println("人脸搜索结果：" + res);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
