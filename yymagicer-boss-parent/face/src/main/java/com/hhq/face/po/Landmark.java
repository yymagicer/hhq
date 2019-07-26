package com.hhq.face.po;

import java.util.ArrayList;
import java.util.List;

public class Landmark {

    private List<Double> left_eye_zhongxin = new ArrayList<Double>();   //左眼中心
    private List<Double> left_eye_top = new ArrayList<Double>();        //左眼上边
    private List<Double> right_eye_top = new ArrayList<Double>();       //右眼上边
    private List<Double> left_eye_bottom = new ArrayList<Double>();     //左眼下边
    private List<Double> right_eye_bottom = new ArrayList<Double>();    //右眼下边
    private List<Double> right_eye_zhongxin = new ArrayList<Double>();  //右眼中心
    private List<Double> nose_zhongxin = new ArrayList<Double>();       //鼻尖
    private List<Double> mouse_zhongxin = new ArrayList<Double>();      //嘴巴中心
    private List<Double> mouse__top = new ArrayList<Double>();          //嘴巴上边
    private List<Double> mouse__bottom = new ArrayList<Double>();       //嘴巴下边

    public List<Double> getLeft_eye_zhongxin() {
        return left_eye_zhongxin;
    }

    public void setLeft_eye_zhongxin(List<Double> left_eye_zhongxin) {
        this.left_eye_zhongxin = left_eye_zhongxin;
    }

    public List<Double> getRight_eye_zhongxin() {
        return right_eye_zhongxin;
    }

    public void setRight_eye_zhongxin(List<Double> right_eye_zhongxin) {
        this.right_eye_zhongxin = right_eye_zhongxin;
    }

    public List<Double> getNose_zhongxin() {
        return nose_zhongxin;
    }

    public void setNose_zhongxin(List<Double> nose_zhongxin) {
        this.nose_zhongxin = nose_zhongxin;
    }

    public List<Double> getMouse_zhongxin() {
        return mouse_zhongxin;
    }

    public void setMouse_zhongxin(List<Double> mouse_zhongxin) {
        this.mouse_zhongxin = mouse_zhongxin;
    }

    public List<Double> getLeft_eye_top() {
        return left_eye_top;
    }

    public void setLeft_eye_top(List<Double> left_eye_top) {
        this.left_eye_top = left_eye_top;
    }

    public List<Double> getRight_eye_top() {
        return right_eye_top;
    }

    public void setRight_eye_top(List<Double> right_eye_top) {
        this.right_eye_top = right_eye_top;
    }

    public List<Double> getLeft_eye_bottom() {
        return left_eye_bottom;
    }

    public void setLeft_eye_bottom(List<Double> left_eye_bottom) {
        this.left_eye_bottom = left_eye_bottom;
    }

    public List<Double> getRight_eye_bottom() {
        return right_eye_bottom;
    }

    public void setRight_eye_bottom(List<Double> right_eye_bottom) {
        this.right_eye_bottom = right_eye_bottom;
    }

    public List<Double> getMouse__top() {
        return mouse__top;
    }

    public void setMouse__top(List<Double> mouse__top) {
        this.mouse__top = mouse__top;
    }

    public List<Double> getMouse__bottom() {
        return mouse__bottom;
    }

    public void setMouse__bottom(List<Double> mouse__bottom) {
        this.mouse__bottom = mouse__bottom;
    }
}
