package com.example.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FastJson {
    //json字符串-简单对象型
    private static final String JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\":12}";

    //json字符串-数组类型
    private static final String JSON_ARRAY_STR = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";

    //复杂格式json字符串
    private static final String COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";

    /**
     * json Str <-> JSONObject 之间的转换
     */
    @Test
    public void testJSONStrTransformJSONObject() {
        //例1：
        //json Str -> JSONObject 的转换
        JSONObject jsonObject = JSON.parseObject(JSON_OBJ_STR);
        String studentName = jsonObject.getString("studentName");
        Integer studentAge = jsonObject.getInteger("studentAge");
        System.out.println(String.format("studentName：%s，studentAge：%s", studentName, studentAge));

        //JSONObject -> json Str 的转换
        String jsonString = JSON.toJSONString(jsonObject);
        System.out.println(jsonString);


        //例2：
        //复杂 json StrComplex -> JSONObject 的转换
        JSONObject jsonObjCom = JSON.parseObject(COMPLEX_JSON_STR);
        String teacherNameCom = jsonObjCom.getString("teacherName");
        Integer teacherAgeCom = jsonObjCom.getInteger("teacherAge");
        System.out.println("teacherName:  " + teacherNameCom + "   teacherAge:  " + teacherAgeCom);

        JSONObject jsonObjCourseCom = jsonObjCom.getJSONObject("course");
        String courseNameCom = jsonObjCourseCom.getString("courseName");
        Integer codeCom = jsonObjCourseCom.getInteger("code");
        System.out.println("courseName:  " + courseNameCom + "   code:  " + codeCom);

        JSONArray jsonArrayStudentCom = jsonObjCom.getJSONArray("students");
        for (Object object : jsonArrayStudentCom) {
            JSONObject jsonObjectOne = (JSONObject) object;
            String studentNameCom = jsonObjectOne.getString("studentName");
            Integer studentAgeCom = jsonObjectOne.getInteger("studentAge");
            System.out.println("studentName:  " + studentNameCom + "   studentAge:  " + studentAgeCom);
        }

        //JSONObject -> 复杂 json StrComplex 的转换
        String jsonStringCom = JSON.toJSONString(jsonObjCom);
        System.out.println(jsonStringCom);
    }

    /**
     * json Str <-> JavaBean 之间的转换
     */
    @Test
    public void testJSONStrTransformJavaBean() {
        //例1：
        //json Str -> JavaBean 的转换
        //方法一：
        Student student = JSON.parseObject(JSON_OBJ_STR, Student.class);
        System.out.println(student);

        //方法二：
        //或者使用 TypeReference<T> 类，由于其构造方法使用 protected 进行修饰，故创建其子类
        Student studentTR = JSON.parseObject(JSON_OBJ_STR, new TypeReference<Student>() {
        });
        System.out.println(studentTR);

        //JavaBean -> json Str 的转换
        String jsonStrStu = JSON.toJSONString(student);
        System.out.println(jsonStrStu);


        //例2：
        //复杂 json StrComplex -> JavaBean 的转换
        //方法一：
        Teacher teacher = JSON.parseObject(COMPLEX_JSON_STR, Teacher.class);
        System.out.println(teacher);

        //方法二：
        Teacher teacherTR = JSON.parseObject(COMPLEX_JSON_STR, new TypeReference<Teacher>() {
        });
        System.out.println(teacherTR);

        //复杂 json StrComplex -> json Str 的转换
        String jsonStrTea = JSON.toJSONString(teacher);
        System.out.println(jsonStrTea);
    }

    /**
     * json StrArray <-> JavaBean_List 之间的转换
     */
    @Test
    public void testJSONStrArrayTransformJavaBeanList() {
        //json StrArray -> JavaBean_List 的转换
        //方法一：遍历（硬编码，不推荐）
        JSONArray jsonArray = JSON.parseArray(JSON_ARRAY_STR);
        List<Student> students = new ArrayList<>();
        Student student;
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            String studentName = jsonObject.getString("studentName");
            Integer studentAge = jsonObject.getInteger("studentAge");
            student = new Student(studentName, studentAge);
            students.add(student);
        }
        System.out.println(students);

        //方法二：或者使用 parseArray（推荐）
        List<Student> studentsArr = JSON.parseArray(JSON_ARRAY_STR, Student.class);
        System.out.println(studentsArr);

        //方法三：或者使用 TypeReference<T> 类，由于其构造方法使用 protected 进行修饰，故创建其子类
        List<Student> studentsTR = JSON.parseObject(JSON_ARRAY_STR, new TypeReference<ArrayList<Student>>() {
        });
        System.out.println(studentsTR);


        //JavaBean_List -> json StrArray 的转换
        String jsonString = JSON.toJSONString(studentsArr);
        System.out.println(jsonString);
    }
}









