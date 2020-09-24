package com.songhao.face.servlet;

import com.songhao.face.util.FaceUtil;
import com.songhao.face.util.ImageUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FaceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //登录步骤：
        //1、获取客户上传的图片，上传到指定文件夹中upimg
        File file = ImageUtils.uploadImg(request,"imgData","upimg");
        boolean res = false;
        boolean delFlag = true;
        //2、判断是否包含人脸信息
        try {
            String faceToken = FaceUtil.detect(file);
            if (faceToken != null){
                res = FaceUtil.search(faceToken);
                String type = request.getParameter("type");
                if ("register".equals(type)) {
                    if (res){
                        res = false;
                    }else {
                        res = FaceUtil.addFace(faceToken);
                        delFlag = false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //删除照片
            file.delete();
            PrintWriter pw = response.getWriter();
            String msg = "{\"success\":"+ res +"}";
            pw.write(msg);
            pw.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
