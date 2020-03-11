package com.yyuanyan.pojo_java;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yyuanyan.pojo_java.model.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;


@SpringBootApplication
public class PojoJavaApplication {

//    private static final Object JSON = "";

    public static void main(String[] args) {

        //SpringApplication.run(PojoJavaApplication.class, args);
        beanTojson();
        jsonToJsonBean();
        jsonBenToJsonObject();

    }

    //java对象转json字符串
    public static void beanTojson() {
        Data data = new Data();
        data.setAction("add");
        data.setId("1");
        data.setOrdinal(8L);
        data.setOrganUnitFullName("testJSON");
        data.setParent("0");
        data.setSuborderNo("58961");

        String s = JSON.toJSONString(data);
        System.out.println("toJsonString()方法：s=" + s);
        //输出结果{"action":"add","id":"1","ordinal":8,"organUnitFullName":"testJSON","parent":"0","suborderNo":"58961"}
    }
    //json字符串转json对象
    public static void jsonToJsonBean() {
        String s ="{\"action\":\"add\",\"id\":\"1\",\"ordinal\":8,\"organUnitFullName\":\"testJSON\",\"parent\":\"0\",\"suborderNo\":\"58961\"}";

        JSONObject jsonObject = JSON.parseObject(s);
        String action = jsonObject.getString("action");
        String id = jsonObject.getString("id");
        System.out.println("action ="+action);//add
        System.out.println("id ="+id);//1
        System.out.println("jsonObject ="+jsonObject);
        //action =add
        //id =1
        //jsonObject ={"parent":"0","organUnitFullName":"testJSON","action":"add","id":"1","suborderNo":"58961","ordinal":8}
    }
    //复杂JSON格式字符串转JAVAObject
    public static void jsonToBean() {
        String str ="{\"meta\":{\"code\":\"0\",\"message\":\"同步成功!\"},\"data\":{\"orderno\":\"U_2018062790915774\",\"suborderno\":\"SUB_2018062797348039\",\"type\":\"organunit\",\"result\":{\"organunit\":{\"totalCount\":2,\"successCount\":0,\"failCount\":2,\"errors\":[{\"code\":\"UUM70004\",\"message\":\"组织单元名称不能为空\",\"data\":{\"id\":\"254\",\"suborderNo\":\"SUB_2018062797348039\",\"organUnitType\":\"部门\",\"action\":\"add\",\"parent\":\"10000\",\"ordinal\":0,\"organUnitFullName\":\"组织单元全称\"},\"success\":false},{\"code\":\"UUM70004\",\"message\":\"组织单元名称不能为空\",\"data\":{\"id\":\"255\",\"suborderNo\":\"SUB_2018062797348039\",\"organUnitType\":\"部门\",\"action\":\"add\",\"parent\":\"10000\",\"ordinal\":0,\"organUnitFullName\":\"组织单元全称\"},\"success\":false}]},\"role\":{\"totalCount\":0,\"successCount\":0,\"failCount\":0,\"errors\":[]},\"user\":{\"totalCount\":0,\"successCount\":0,\"failCount\":0,\"errors\":[]}}}}";
        JSONObject jsonObject = JSON.parseObject(str);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONObject result = data.getJSONObject("result");

        String organunit1 = result.getString("organunit");
        System.out.println(organunit1);
        JSONObject organunit = result.getJSONObject("organunit");

        JSONArray errors2 = organunit.getJSONArray("errors");

        List<Error> error = JSON.parseObject(errors2.toJSONString(), new TypeReference<List<Error>>() {
        });
    }

    //json字符串转java简单对象
    public static void jsonStrToJavaBean() {
        String s ="{\"action\":\"add\",\"id\":\"1\",\"ordinal\":8,\"organUnitFullName\":\"testJSON\",\"parent\":\"0\",\"suborderNo\":\"58961\"}";
        Data data = JSON.parseObject(s, Data.class);
        System.out.println("data对象"+data.toString());
        System.out.println("action="+data.getAction()+"---id="+data.getId());
        //data对象Data{id='1', suborderNo='58961', organUnitType='null', action='add', parent='0', organUnitFullName='testJSON', ordinal=8}
        //action=add---id=1

        /**
         * 另一种方式转对象
         */
        Data dd = JSON.parseObject(s, new TypeReference<Data>() {});
        System.out.println("另一种方式获取data对象"+dd.toString());
        System.out.println("另一种方式获取="+dd.getAction()+"---id="+dd.getId());
        //另一种方式获取data对象Data{id='1', suborderNo='58961', organUnitType='null', action='add', parent='0', organUnitFullName='testJSON', ordinal=8}
        //另一种方式获取=add---id=1
    }

    //json字符串--数组型与JSONArray对象之间的转换
    public static void jsonStrToJSONArray() {
        String str = "{\"errors\":[{\"code\":\"UUM70004\",\"message\":\"组织单元名称不能为空\",\"data\":{\"id\":\"254\",\"suborderNo\":\"SUB_2018062797348039\",\"organUnitType\":\"部门\",\"action\":\"add\",\"parent\":\"10000\",\"ordinal\":0,\"organUnitFullName\":\"组织单元全称\"},\"success\":false},{\"code\":\"UUM70004\",\"message\":\"组织单元名称不能为空\",\"data\":{\"id\":\"255\",\"suborderNo\":\"SUB_2018062797348039\",\"organUnitType\":\"部门\",\"action\":\"add\",\"parent\":\"10000\",\"ordinal\":0,\"organUnitFullName\":\"组织单元全称\"},\"success\":false}]}";
        JSONObject jsonObject = JSON.parseObject(str);
        JSONArray error = jsonObject.getJSONArray("errors");
        List<Error> errors = JSON.parseObject(error.toJSONString(), new TypeReference<List<Error>>() {
        });/*
        for (Error e: errors) {
            //Error的属性
            System.out.println("Error属性="+e.getSuccess());
            System.out.println("Error属性="+e.getCode());
            System.out.println("Error属性="+e.getMessage());
            //Error集合属性
            List<Data> datas = e.getData();
            for (Data d: datas) {
                System.out.println("data对象属性="+d.getId());
                System.out.println("data对象属性="+d.getAction());
                System.out.println("data对象属性="+d.getSuborderNo());
            }
        }*/
        //Error属性=false
        //Error属性=UUM70004
        //Error属性=组织单元名称不能为空
        //data对象属性=254
        //data对象属性=add
        //data对象属性=SUB_2018062797348039

        //Error属性=false
        //Error属性=UUM70004
        //Error属性=组织单元名称不能为空
        //data对象属性=255
        //data对象属性=add
        //data对象属性=SUB_2018062797348039
    }

    //第二种方法：json字符串--数组型与JSONArray对象之间的转换
    @Test
    public void jsonStrToJSONArray2() {
        String str = "{\"errors\":[{\"code\":\"UUM70004\",\"message\":\"组织单元名称不能为空\",\"data\":{\"id\":\"254\",\"suborderNo\":\"SUB_2018062797348039\",\"organUnitType\":\"部门\",\"action\":\"add\",\"parent\":\"10000\",\"ordinal\":0,\"organUnitFullName\":\"组织单元全称\"},\"success\":false},{\"code\":\"UUM70004\",\"message\":\"组织单元名称不能为空\",\"data\":{\"id\":\"255\",\"suborderNo\":\"SUB_2018062797348039\",\"organUnitType\":\"部门\",\"action\":\"add\",\"parent\":\"10000\",\"ordinal\":0,\"organUnitFullName\":\"组织单元全称\"},\"success\":false}]}";
        //获取jsonobject对象
        JSONObject jsonObject = JSON.parseObject(str);
        //把对象转换成jsonArray数组
        JSONArray error = jsonObject.getJSONArray("errors");
        //error==>[{"code":"UUM70004","message":"组织单元名称不能为空","data":{"id":"254","suborderNo":"SUB_2018062797348039","organUnitType":"部门","action":"add","parent":"10000","ordinal":0,"organUnitFullName":"组织单元全称"},"success":false},{"code":"UUM70004","message":"组织单元名称不能为空","data":{"id":"255","suborderNo":"SUB_2018062797348039","organUnitType":"部门","action":"add","parent":"10000","ordinal":0,"organUnitFullName":"组织单元全称"},"success":false}]
        //将数组转换成字符串
        String jsonString = JSONObject.toJSONString(error);//将array数组转换成字符串
        //将字符串转成list集合
        List<Error>  errors = JSONObject.parseArray(jsonString, Error.class);//把字符串转换成集合
        for (Error e: errors) {
            //Error的属性
            /*System.out.println("另一种数组转换Error属性="+e.getSuccess());
            System.out.println("另一种数组转换Error属性="+e.getCode());*/
            System.out.println("另一种数组转换Error属性="+e.getMessage());
            //Error集合属性
            /*List<Data> datas = e.getData();
            for (Data d: datas) {
                System.out.println("另一种数组转换data对象属性="+d.getId());
                System.out.println("另一种数组转换data对象属性="+d.getAction());
                System.out.println("另一种数组转换data对象属性="+d.getSuborderNo());
            }*/
        }
        //另一种数组转换Error属性=false
        //另一种数组转换Error属性=UUM70004
        //另一种数组转换Error属性=组织单元名称不能为空
        //另一种数组转换data对象属性=254
        //另一种数组转换data对象属性=add
        //另一种数组转换data对象属性=SUB_2018062797348039

        //另一种数组转换Error属性=false
        //另一种数组转换Error属性=UUM70004
        //另一种数组转换Error属性=组织单元名称不能为空
        //另一种数组转换data对象属性=255
        //另一种数组转换data对象属性=add
        //另一种数组转换data对象属性=SUB_2018062797348039
    }

    /*！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！*/
    //javabean转json对象
    public static void jsonBenToJsonObject() {
        Data data = new Data();
        data.setAction("add");
        data.setId("1");
        data.setOrdinal(8L);
        data.setOrganUnitFullName("testJSON");
        data.setParent("0");
        data.setSuborderNo("58961");
        JSONObject jsonObj = (JSONObject) JSON.toJSON(data);
        JSON json = (JSON) JSON.toJSON(data);
        System.out.println("jsonObj"+jsonObj);
        System.out.println("json对象"+json);
        //jsonObj{"parent":"0","organUnitFullName":"testJSON","action":"add","id":"1","suborderNo":"58961","ordinal":8}
        //json对象{"parent":"0","organUnitFullName":"testJSON","action":"add","id":"1","suborderNo":"58961","ordinal":8}
    }

}
