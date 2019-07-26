package com.hhq.face.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hhq.common.base.PageQueryParam;
import com.hhq.face.po.FaceInfoPO;
import com.hhq.face.po.Landmark;
import com.hhq.face.service.FaceService;
import com.hhq.face.util.JsonParseUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * faceservice 实现
 */
@Service
public class FaceServiceImpl implements FaceService {
    @Override
    public int save(FaceInfoPO faceInfoPO) {
        return 0;
    }

    @Override
    public FaceInfoPO get(FaceInfoPO faceInfoPO) {
        return null;
    }

    @Override
    public int update(FaceInfoPO faceInfoPO) {
        return 0;
    }

    @Override
    public int delete(FaceInfoPO faceInfoPO) {
        return 0;
    }

    @Override
    public List<FaceInfoPO> queryList(FaceInfoPO faceInfoPO) {
        return null;
    }

    @Override
    public PageInfo<FaceInfoPO> queryListByPage(PageQueryParam<FaceInfoPO> param) {
        return null;
    }

    @Override
    public int batchDelete(List<String> ids, String userId, String userName) {
        return 0;
    }

    /**
     *
     * @Description: 该方法的主要作用：张张嘴验证
     * @Title: face_jiance
     * @param  @param img
     * @param  @param response
     * @param  @param request 设定文件
     * @return  返回类型：void
     * @throws
     */
    private void face_mouse(String img, HttpServletResponse response,
                            HttpServletRequest request) {

        /*if (dataMap.get(1) == null) {
            // 第一次请求
            landmark = face_jiance(img);
            System.out.println("第一次的上嘴唇："+landmark.getMouse__top().get(0));
            System.out.println("第一次的嘴唇中心："+landmark.getMouse_zhongxin().get(0));
            System.out.println("第一次的下嘴唇："+landmark.getMouse__bottom().get(0));
            dataMap.put(1, landmark);
        } else {*/
        // 不是第一次请求
        Landmark landmark_next = JsonParseUtil.parsingFaceJson(JSONObject.parseObject(img));;
        // 和前一次的数据进行比较
        //Landmark landmark_pre = (Landmark) dataMap.get(1);
        // 嘴唇上面的位置相对于中心点对比
        PrintWriter writer;
        if ((landmark_next.getMouse_zhongxin().get(0) - landmark_next
                .getMouse__top().get(0)) > 40&&(landmark_next.getMouse__bottom().get(0) - landmark_next
                .getMouse_zhongxin().get(0)) > 30) {

            try {
                writer = response.getWriter();
                writer.print("1");
            } catch (IOException e) {
                // TODO 异常执行块！
                e.printStackTrace();
            }
        }else{
            try {
                writer = response.getWriter();
                writer.print("0");
            } catch (IOException e) {
                // TODO 异常执行块！
                e.printStackTrace();
            }
        }
        //dataMap.put(1,landmark_next);         //放进去  方便下次进行比较
    }

    /**
     *
     * @Description: 该方法的主要作用：眨眼对比
     * @Title: face_eye
     * @param  @param img
     * @param  @param response
     * @param  @param request 设定文件
     * @return  返回类型：void
     * @throws
     */
    private void face_eye(String img, HttpServletResponse response,
                          HttpServletRequest request) {
        Landmark landmark_next = JsonParseUtil.parsingFaceJson(JSONObject.parseObject(img));
        System.out.println("闭着眼的左眼上边与中间的值："+((landmark_next.getLeft_eye_zhongxin().get(0))-(landmark_next.getLeft_eye_top().get(0))));
        System.out.println("闭着眼的左眼下边与中间的值："+((landmark_next.getLeft_eye_bottom().get(0))-(landmark_next.getLeft_eye_zhongxin().get(0))));
        System.out.println("闭着眼的右眼上边与中间的值："+((landmark_next.getRight_eye_zhongxin().get(0))-(landmark_next.getRight_eye_top().get(0))));
        System.out.println("闭着眼的右眼下边与中间的值："+((landmark_next.getRight_eye_bottom().get(0))-(landmark_next.getRight_eye_zhongxin().get(0))));
        System.out.println("__________________________________________________");
        PrintWriter writer;
        if ((landmark_next.getLeft_eye_zhongxin().get(0))
                - (landmark_next.getLeft_eye_top().get(0)) < 6
                && (landmark_next.getRight_eye_zhongxin().get(0))
                - (landmark_next.getRight_eye_top().get(0)) < 6) {
            System.out.println("进来了上边验证成功");
            // 继续判断下边的
            if ((landmark_next.getLeft_eye_bottom().get(0))
                    - (landmark_next.getLeft_eye_zhongxin().get(0)) < 6.6
                    && (landmark_next.getRight_eye_bottom().get(0))
                    - (landmark_next.getRight_eye_zhongxin().get(0)) < 6.6) {
                System.out.println("进来了下边验证成功");
                try {
                    writer = response.getWriter();
                    writer.print("1");
                } catch (IOException e) {
                    // TODO 异常执行块！
                    e.printStackTrace();
                }
            } else {
                try {
                    writer = response.getWriter();
                    writer.print("0");
                } catch (IOException e) {
                    // TODO 异常执行块！
                    e.printStackTrace();
                }
            }
        }
    }
}
